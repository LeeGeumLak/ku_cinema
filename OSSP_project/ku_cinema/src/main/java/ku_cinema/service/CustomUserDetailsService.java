package ku_cinema.service;

import java.util.ArrayList;
import java.util.List;
// for authority List

import ku_cinema.model.*;
import ku_cinema.repository.*;
// for service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// autowired + service 

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// for spring security...

@Service
public class CustomUserDetailsService implements UserDetailsService{
		// userdetailsservice도 뜯어봐야 할듯.. 히히!
	private static final String ROLE_PREFIX = "ROLE_";
	// final ... constant...
	
	@Autowired
	customerRepo customerrepo;
	@Autowired
	authorRepo authorrepo;
	
	private static List<GrantedAuthority> makeGrantedAuthority(String role){
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
		// i have to understand this code...
		// must add ROLE_... to make spring understand...
		return list;
	}
	
	@Override
	public UserDetails loadUserByUsername(String ID) throws UsernameNotFoundException {
		
		customer cus = customerrepo.findByID(ID);
		if (cus != null) {
			cus.setAuthorities(makeGrantedAuthority(authorrepo.findByID(ID).getRole()));
		}
		
		return new SecurityCustomer(cus);
		
	}

}

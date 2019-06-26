package ku_cinema.model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityCustomer extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 126L;

	private String ip;
	
	public SecurityCustomer(customer cus) {
		
		super(cus.getID(), cus.getPW(), cus.getAuthorities());
//		super(cus.getID(), cus.getPW(), makeGrantedAuthority(cus.getRoles()));
		
	}
	// User. constructor 2개.
/*	
	private static List<GrantedAuthority> makeGrantedAuthority(List<author> roles){
		List<GrantedAuthority> list = new ArrayList<> ();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole())));
		return list;
	}
*/	
	// 추후 controller에서 사용하는 customer 객체는 이 security customer가 될 것.
	// 필요한 data는 여기에 넣어 두어야 사용 가능. 그래서 일단은 ip를 넣어둠.
	
	// security에서 제공하는 user 객체를 사용해야 하고, (userdetails)
	// security에서 제공하는 user service를 사용해서 user 객체를 다뤄야 한다. (userdetailservice)

}


//reference : https://4urdev.tistory.com/53
// reference : https://xmfpes.github.io/spring/spring-security/
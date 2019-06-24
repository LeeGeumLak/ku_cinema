package ku_cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ku_cinema.model.customer;
import ku_cinema.repository.customerRepo;

@Service
public class BookingUserDetailsService implements UserDetailsService {
	@Autowired
    customerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        customer customer = customerRepo.findOne(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        return new BookingUserDetails(customer);
    }
}

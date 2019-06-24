package ku_cinema.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import ku_cinema.model.customer;

public class BookingUserDetails implements UserDetails {
    private static final long serialVersionUID = 2908958712533080903L;
    private final customer customer;

    public BookingUserDetails(customer customer) {
        this.customer = customer;
    }

    public customer getUser() {
        return customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils
                .createAuthorityList("ROLE_" + this.customer.getRoleName().name());
    }

    @Override
    public String getPassword() {
        return this.customer.getPW();
    }

    @Override
    public String getUsername() {
        return this.customer.getID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
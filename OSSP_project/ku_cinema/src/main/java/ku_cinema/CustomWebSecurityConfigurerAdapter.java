package ku_cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// for spring bean... + DI

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// for spring security...


import ku_cinema.service.CustomUserDetailsService;
// add customeuserdetail.

@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customuserdetailsservice;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
<<<<<<< Updated upstream
		web.ignoring().antMatchers("/resource/**");
=======
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/**");
>>>>>>> Stashed changes
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
<<<<<<< Updated upstream
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
=======
		http.authorizeRequests()
				.antMatchers("/ku_cinema/signup").anonymous()
				.antMatchers("/ku_cinema/mypage/**", "/ku_cinema/reservation/**").hasAnyRole("user", "admin")
				.and().formLogin()
					.loginPage("/ku_cinema/login")
					.usernameParameter("ID")	
					.passwordParameter("PW")
					.loginProcessingUrl("/ku_cinema/")
					.defaultSuccessUrl("/ku_cinema/")
					.failureUrl("/ku_cinema/login")
				.and()
					.logout()
					.logoutSuccessUrl("/ku_cinema/");

	}
	
	// reference: https://gs.saro.me/dev?page=20&tn=480
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
>>>>>>> Stashed changes
		auth.userDetailsService(customuserdetailsservice).passwordEncoder(passwordEncoder());
	}

}

package ku_cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// for spring bean... + DI

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
		web.ignoring().antMatchers("/resources/**");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().antMatchers("/ku_cinema/login").authenticated().antMatchers("/**").permitAll();
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
		http.authorizeRequests()
			.antMatchers("/**").authenticated();
			//.antMatchers("/ku_cinema/reservation/**", "/ku_cinema/mypage/**", "/ku_cinema/**").denyAll()
			
		//.antMatchers("/ku_cinema/reservation/**", "/ku_cinema/mypage/**", "ku_cinema/login").authenticated()
/*			.and().formLogin()
			.loginPage("/ku_cinema/login")
			.loginProcessingUrl("/ku_cinema/login")
			.defaultSuccessUrl("/ku_cinema/")
			.failureUrl("/ku_cinema/login")
			.usernameParameter("ID")	
			.passwordParameter("PW")
		.permitAll()
		.and()
		.logout()
			.logoutSuccessUrl("/ku_cinema/")*/
	//	.anyRequest().permitAll();


	//	.permitAll();

	}
	
	// reference: https://gs.saro.me/dev?page=20&tn=480
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customuserdetailsservice).passwordEncoder(passwordEncoder());
	}

}

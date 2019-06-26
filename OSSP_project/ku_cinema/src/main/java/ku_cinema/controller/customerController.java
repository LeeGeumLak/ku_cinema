package ku_cinema.controller;

import ku_cinema.model.*;
import ku_cinema.service.*;

//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("ku_cinema")
public class customerController {

	@Autowired
	customerService customerservice;
	@Autowired
	authorService authorservice;
	@Autowired
	CustomUserDetailsService customuserdetailsservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String mainpage(Model model) {
		
		Object Principal = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		/*
		if(Principal instanceof UserDetails)
			((UserDetails) Principal).getAuthorities();

				  	???????

		 */
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String Loginpage() {
		return "login";
	}
	
	/*@RequestMapping(value = "/", method = RequestMethod.POST)
	String LoginSuccess(String ID, String PW, Model model) {
		customer cus = customerservice.FindUser(ID);
		if((cus!= null) && (cus.getPW()).equals(PW))
			return "index";
		else
			return "redirect:login";
	}*/
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	String Security_Login(String ID, String PW, Model model) {
		// SecurityCustomer s_cus = customuserdetailsservice.loadUserByUsername(ID);
		
		BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
<<<<<<< Updated upstream
		 if(crypto.matches(PW, customuserdetailsservice.loadUserByUsername(ID).getPassword()))
=======
		String B_PW = crypto.encode(PW);
		System.out.println(B_PW);
		 if(customuserdetailsservice.loadUserByUsername(ID) != null && 
				 crypto.matches(PW, customuserdetailsservice.loadUserByUsername(ID).getPassword())) {
>>>>>>> Stashed changes
			 return "index";
		 }
		 else
			 return "redirect:login";
	}
	
	@GetMapping(value = "/{ID}")
	public @ResponseBody String authorconfig(@PathVariable String ID) {
		
		author auth = authorservice.FindAuthorUser(ID);
		return auth.getRole();
	}
<<<<<<< Updated upstream
=======
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	String SignPage() {
		return "signup";
	}
	
	@RequestMapping(value = "/signsuccess", method = RequestMethod.POST)
	public String SuccessSign(customer cus) {
	
		author au = new author();
		
		au.setRole("user");
		au.setID(cus.getID());
		
		authorservice.AssignAu(au);
			
		BCryptPasswordEncoder PE = new BCryptPasswordEncoder();
		cus.setPW(PE.encode(cus.getPW()));
			
	    customerservice.AssignUser(cus);
			
		return "signup-success";
		
	}
>>>>>>> Stashed changes
}
package ku_cinema.controller;

import ku_cinema.model.*;
import ku_cinema.service.*;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String mainpage() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String Loginpage() {
		return "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	String LoginSuccess(String ID, String PW, Model model) {
		customer cus = customerservice.FindUser(ID);
		if((cus!= null) && (cus.getPW()).equals(PW))
			return "index";
		else
			return "redirect:login";
	}
	
	@GetMapping(value = "/{ID}")
	public @ResponseBody String authorconfig(@PathVariable String ID) {
		
		author auth = authorservice.FindAuthorUser(ID);
		return auth.getRole();
	}
}
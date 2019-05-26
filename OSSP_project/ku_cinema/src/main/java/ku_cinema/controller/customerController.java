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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String main() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String Login(String ID, String PW) {
		customer cus = customerservice.FindUser(ID);
		if(cus!=null && cus.getPW().equals(PW))
			return "index";
		else
			return "login";
	}
	
}

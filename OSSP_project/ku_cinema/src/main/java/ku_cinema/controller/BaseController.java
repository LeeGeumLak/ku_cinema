package ku_cinema.controller;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
		
	@RequestMapping("/ku_cinema/base")
    public ModelAndView getBase(){
    	ModelAndView m=new ModelAndView("base");
	   	return m;
	}
	
}

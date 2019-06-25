package ku_cinema.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ku_cinema.model.Admin;
import ku_cinema.model.BookingDetails;
import ku_cinema.model.Feedbacks;
import ku_cinema.model.Movies;
import ku_cinema.model.Roles;
import ku_cinema.model.User;
import ku_cinema.model.impl.AdminDaoImpl;
import ku_cinema.model.impl.BookingDetailsDaoImpl;
import ku_cinema.model.impl.FeedbacksDaoImpl;
import ku_cinema.model.impl.MoviesDaoImpl;
import ku_cinema.model.impl.RolesDaoImpl;
import ku_cinema.model.impl.UserDaoImpl;

@Controller
@SessionAttributes(value = {"ad","profile"})
public class AdminController {
	
		@Autowired
		private MoviesDaoImpl mi;
		
		@Autowired
		private RolesDaoImpl ri;
		
		@Autowired
		private FeedbacksDaoImpl fi;
	
		@Autowired
		private UserDaoImpl ui;
		
		@Autowired
		private AdminDaoImpl ai;
		
		@Autowired
		private BookingDetailsDaoImpl bi;
		
		@RequestMapping(value="/ku_cinema/admin-login")
		public String getAdminLogin(){
			return "admin-login";
		}
		
		@RequestMapping(value=("/ku_cinema/admin"),method=RequestMethod.GET)
		@ResponseBody
		public ModelAndView getAdminDashboard(Principal principal,Admin admin){
			ModelAndView m=new ModelAndView("admin");
			admin=ai.getAdminById(principal.getName());
			if(admin.getGender().equals("female")){
				m.addObject("profile","profile-female");
			
			}
			else if(admin.getGender().equals("male")){
				m.addObject("profile","profile-male");
				
			}
			else{
				m.addObject("profile","profile");
			}
			
			m.addObject("ad",admin.getName());
			return m;
		}
		
		@RequestMapping(value=("/ku_cinema/admin/manage-movies"),method=RequestMethod.GET)
		public ModelAndView getManageMovies(@ModelAttribute("movie")Movies movie,Map<String, Object> model){
			List<Movies> list=mi.getAllMovies();
			List<String> s= new ArrayList<String>();
			s.add("Status");
			s.add("Released");
			s.add("Upcoming");
			model.put("status",s);
			model.put("list",list);
			ModelAndView m=new ModelAndView("manage-movies");
			return m;
		}
		
		@RequestMapping(value=("/ku_cinema/admin/registerMovie"),method=RequestMethod.POST)
		public ModelAndView registerMovie(Movies movie){
			boolean res=mi.insert(movie);
			ModelAndView m=new ModelAndView("redirect:/ku_cinema/admin/manage-movies");
			if(res==true){
				m.addObject("msg","added");
			}else {
				m.addObject("msg","Cannot add movie re-submit");
			}
			return m;
		}
		
		@RequestMapping(value=("/ku_cinema/admin/updateMovie"),method=RequestMethod.POST)  
	    public ModelAndView updateMovie(Movies mov){
	        mi.update(mov); 
	        return new ModelAndView("redirect:/ku_cinema/admin/manage-movies");  
	    }
		
		@RequestMapping(value=("/ku_cinema/admin/deleteMovie/{id}"),method=RequestMethod.GET)  
		public ModelAndView deleteMovie(@PathVariable("id") long id){
		    mi.delete(id); 
		    return new ModelAndView("redirect:/ku_cinema/admin/manage-movies");  
		}
		
		@RequestMapping("/ku_cinema/admin/manage-feedbacks")
		public ModelAndView getManageFeedbacks(Map<String, Object> model){
			List<Feedbacks> list=fi.getAllFeedbacks();
			model.put("list",list);
			ModelAndView m=new ModelAndView("manage-feedbacks");
			return m;
		}
		
		@RequestMapping(value=("/ku_cinema/admin/deleteFeedback/{id}"),method=RequestMethod.GET)  
		public ModelAndView deleteFeedback(@PathVariable("id") long id){
		    fi.delete(id);
		    return new ModelAndView("redirect:/ku_cinema/admin/manage-feedbacks");  
		 }
		
		@RequestMapping("/ku_cinema/admin/manage-bookings")
		public String getManageReports(BookingDetails bd,Map<String, Object> model){
			List<BookingDetails> list=bi.getAllBookingDetails();
			model.put("list",list);
			return "manage-bookings";
		}
		
		@RequestMapping(value=("/ku_cinema/admin/deleteBooking/{id}"),method=RequestMethod.GET)  
		public ModelAndView deleteBooking(@PathVariable("id") long id){
		    bi.delete(id);
		    return new ModelAndView("redirect:/ku_cinema/admin/manage-bookings");  
		 }
		
		
		@RequestMapping("/ku_cinema/admin/manage-users")
		public ModelAndView getManageUsers(@ModelAttribute("user")User user,Map<String, Object> model){
			List<User> list=ui.getAllUser();
			model.put("list",list);
			ModelAndView m=new ModelAndView("manage-users");
			return m;
		}
		
		@RequestMapping(value=("/ku_cinema/admin/updateUser"),method=RequestMethod.POST)  
	    public ModelAndView updateUser(User user){
	        ui.update(user); 
	        return new ModelAndView("redirect:/ku_cinema/admin/manage-users");  
	    }
		
		@RequestMapping(value=("/ku_cinema/admin/deleteUser/{id}"),method=RequestMethod.GET)  
		public ModelAndView deleteUser(@PathVariable("id") long id){
		    ui.delete(id);
		    return new ModelAndView("redirect:/ku_cinema/admin/manage-users");
		}
		
		@RequestMapping("/ku_cinema/logout")
		public ModelAndView getLogout(){
			ModelAndView m=new ModelAndView("/ku_cinema/admin");
			return m;
		}
		
		@RequestMapping("/ku_cinema/logFail")
		public ModelAndView getLogFail(){
			ModelAndView m=new ModelAndView("/ku_cinema/admin");
			return m;
		}
	
		@RequestMapping(value="/ku_cinema/admin/add",method=RequestMethod.GET)
		public ModelAndView addAdmin(@ModelAttribute("admin")Admin admin){
			ModelAndView m=new ModelAndView("admin-registration");
			return m;
		}
		
		@RequestMapping(value="/ku_cinema/admin/registerAdmin")
		public ModelAndView registerAdmin(Admin admin){
			boolean res=ai.insert(admin);
			Roles role=new Roles();
			role.setEmail(admin.getId());
			role.setRole("ROLE_ADMIN");
			if(res==true){
				ri.insert(role);
			}
			ModelAndView m=new ModelAndView("redirect:/ku_cinema/admin/add");
			return m;
		}
		
		@RequestMapping(value="/ku_cinema/admin/upload",method=RequestMethod.POST)
		public ModelAndView upload(@RequestParam("file")CommonsMultipartFile file,@RequestParam String selection,HttpSession session,HttpServletRequest request){
		
			String path=session.getServletContext().getRealPath("/resources/static/img/thumb/");
			
			//String fileName=file.getOriginalFilename();
			
			Long id=Long.parseLong(selection);
			Movies movie=mi.getMovieById(id);
			movie.setImage(selection);
			String fileName=selection;
						
			System.out.println(path);	
			
			try{
				File f = new File(path+fileName+".jpg");
				if(f.exists()){
					boolean res=f.delete();
					if(res){
						System.out.println("Deleted");
					}
					byte img[]=file.getBytes();
					BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(path+fileName+".jpg"));
					out.write(img);
					out.flush();
					out.close();
					mi.update(movie);
				}
				else{
					byte img[]=file.getBytes();
					BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(path+fileName+".jpg"));
					out.write(img);
					out.flush();
					out.close();
					mi.update(movie);
				}

			}catch(Exception e){
				e.printStackTrace();
			}
			ModelAndView m=new ModelAndView("redirect:/ku_cinema/admin/manage-movies");
			return m;
			
		}	   	
}

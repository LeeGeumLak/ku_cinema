package ku_cinema.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ku_cinema.model.BookingDetails;
import ku_cinema.model.Cinemas;
import ku_cinema.model.Movies;
import ku_cinema.model.Seats;
import ku_cinema.model.ShowTimings;
import ku_cinema.model.impl.BookingDetailsDaoImpl;
import ku_cinema.model.impl.CinemasDaoImpl;
import ku_cinema.model.impl.MoviesDaoImpl;
import ku_cinema.model.impl.SeatsDaoImpl;
import ku_cinema.model.impl.ShowTimingsDaoImpl;
import ku_cinema.model.impl.UserDaoImpl;
import ku_cinema.service.DateTime;

@Controller
@SessionAttributes(value = {"ms", "cs","ss","seats","price","count","cns","total","book"})
public class BookingController {

	@Autowired
	CinemasDaoImpl ci;
	
	@Autowired
	UserDaoImpl ui;
	
	@Autowired
	MoviesDaoImpl mi;
	
	@Autowired
	SeatsDaoImpl si;
	
	@Autowired
	ShowTimingsDaoImpl ti;
	
	@Autowired
	DateTime dt; 
	
	@Autowired
	BookingDetailsDaoImpl bi;
	
	@RequestMapping(value="/ku_cinema/selectCinema/{id}",method=RequestMethod.GET)
	public ModelAndView chooseCinema(@PathVariable("id")long id,Movies movie,Map<String, Object> model){
		ModelAndView m=new ModelAndView("select-cinema");
		movie=mi.getMovieById(id);
		List<Cinemas> list=ci.getAllCinemas();
		model.put("list",list);
		m.addObject("ms",movie);
		return m;
	}
	
	@RequestMapping(value="/ku_cinema/selectSeats/{time}/{cinema}/{location}",method=RequestMethod.GET)
	public ModelAndView chooseSeats(@PathVariable("time")String time,@PathVariable("cinema")String cinema,@PathVariable("location")String location,@ModelAttribute Movies movie,Map<String, Object> model,ShowTimings show){
		String cn="";
		cn=cinema+" - "+location;
		ModelAndView m=new ModelAndView("select-seats");
		show.setTime(time);
		ti.insert(show);
		List<Seats> list=si.getAllSeats();
		
		String current=dt.time();
		model.put("list",list);
		m.addObject("ss",show);
		m.addObject("cns",cn);
		m.addObject("current",current);
		return m;
	}
	
	@RequestMapping("/ku_cinema/makePayment/{id}/{cinema}/{seats}/{total}/{sdate}/{stime}/{count}/{session:.+}")
	public ModelAndView makePayment(BookingDetails book,@PathVariable("id")long id,@PathVariable("cinema")String cinema,@PathVariable("seats")String seats,@PathVariable("total")long total,@PathVariable("sdate")String sdate,@PathVariable("stime")String stime,@PathVariable("combo")String combo,@PathVariable("count")long count,@PathVariable("session")String session){
		Movies movie=mi.getMovieById(id);
		book.setMovie(movie.getTitle());
		String amt=""+total;
		book.setAmount(amt);
		book.setCinema(cinema);
		String cnt=""+count;
		book.setCount(cnt);
		book.setShowDate(sdate);
		book.setShowTime(stime);
		book.setDate(dt.date());
		book.setTime(dt.time());
		book.setSeats(seats);
		book.setStatus("Done");
		
		if(session.equals(movie.getTitle())){
			ModelAndView m=new ModelAndView("confirm-login");
			bi.insert(book);
			m.addObject("book",book);
			return m;
		}
		else{
			
			book.setEmail(session);
			bi.insert(book);
			ModelAndView m=new ModelAndView("make-payment");
			m.addObject("book",book);
			return m;
		}
	}
	
	@RequestMapping("/ku_cinema/getTicket")
	public ModelAndView getTicket(){
		
		ModelAndView m=new ModelAndView("getTicket");
		return m;
	}
	
	@RequestMapping("/ku_cinema/bookingHistory/{email:.+}")
	public ModelAndView getBookingHistory(@PathVariable("email")String email,Map<String, Object> model){
		List<BookingDetails> list=bi.getAllBookingByUser(email);
		model.put("history",list);
		ModelAndView m=new ModelAndView("booking-history");
		return m;
	}
	
}

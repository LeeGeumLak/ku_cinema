package ku_cinema.repository.booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ku_cinema.model.AvailableMovieId;
import ku_cinema.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{
	List<Booking> findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(AvailableMovieId availableMovieId); 
}

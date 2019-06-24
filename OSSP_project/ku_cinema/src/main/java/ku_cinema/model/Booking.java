package ku_cinema.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Booking implements Serializable{
	private static final long serialVersionUID = -5025188874217945645L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private LocalDate bookedDate;
	 
	@ManyToOne
	@JoinColumns({ @JoinColumn(name="available_date"),
		@JoinColumn(name="movie_id") })
	private AvailableMovie availableMovie;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private customer customer;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public AvailableMovie getAvailableMovie() {
		return availableMovie;
	}

	public void setAvailableMovie(AvailableMovie availableMovie) {
		this.availableMovie = availableMovie;
	}

	public customer getUser() {
		return customer;
	}

	public void setUser(customer customer) {
		this.customer = customer;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}
		
}

package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.BookingDetails;

public interface BookingDetailsDao {

	public boolean insert(BookingDetails bd);
	public List<BookingDetails> getAllBookingDetails();
	public List<BookingDetails> getAllBookingByUser(String email);
	public boolean delete(long bookingDetailId);
	public boolean update(BookingDetails bd);
	public BookingDetails getBookingDetailById(String bookingDetailId);
	
}

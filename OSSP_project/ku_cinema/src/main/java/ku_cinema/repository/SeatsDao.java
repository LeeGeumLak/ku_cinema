package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.Seats;

public interface SeatsDao {
	
	public boolean insert(Seats seat);
	public List<Seats> getAllSeats();
	public boolean update(Seats seat);
	public Seats getSeatById(String seatId);

}

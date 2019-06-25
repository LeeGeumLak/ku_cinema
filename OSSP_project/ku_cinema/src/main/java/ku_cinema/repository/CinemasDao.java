package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.Cinemas;

public interface CinemasDao{
	
	public boolean insert(Cinemas cinema);
	public List<Cinemas> getAllCinemas();
	public boolean delete(long cinemaId);
	public boolean update(Cinemas cinema);
	public Cinemas getCinemaById(String cinemaId);
}

package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.ShowTimings;

public interface ShowTimingsDao{

	public boolean insert(ShowTimings show);
	public List<ShowTimings> getAllShowTimings();
	public boolean delete(String showId);
	public boolean update(ShowTimings show);
	public ShowTimings getShowById(String showId);
	
}

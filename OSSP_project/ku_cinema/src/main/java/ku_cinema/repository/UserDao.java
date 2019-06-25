package ku_cinema.repository;

import java.util.List;

import ku_cinema.model.User;

public interface UserDao {
	
	public boolean insert(User user);
	public List<User> getAllUser();
	public boolean delete(long userId);
	public boolean update(User user);
	public User getUserById(String userId);
	
}

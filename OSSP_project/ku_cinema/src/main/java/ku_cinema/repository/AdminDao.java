package ku_cinema.repository;

import ku_cinema.model.Admin;

public interface AdminDao {

	public boolean insert(Admin admin);
	public Admin getAdminById(String adminId);

}

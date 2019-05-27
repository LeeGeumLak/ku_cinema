package ku_cinema.repository;

import ku_cinema.model.*;
import org.springframework.data.repository.CrudRepository;

public interface authorRepo extends CrudRepository<author, String>{
	
	author findByID(String ID);

}

package ku_cinema.repository;

import ku_cinema.model.*;
import org.springframework.data.repository.CrudRepository;
/* 	we'll create, read, update, delete entity by CrudRepository. 	*/
/*  reference URL : https://spring.io/guides/gs/accessing-data-jpa/ */
//import org.springframework.data.repository.query.Param;

public interface customerRepo extends CrudRepository<customer, String>{
	
	customer findByID(String ID);

	
}
	
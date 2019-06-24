package ku_cinema.service;

import ku_cinema.model.*;
import ku_cinema.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
/* reference URL: https://spring.io/guides/gs/managing-transactions/ */


@Service
public class customerService {

	@Autowired
	customerRepo customerrepo;
	
	@Transactional
	public customer FindUser(String ID) {
		return customerrepo.findByID(ID);
	}
}

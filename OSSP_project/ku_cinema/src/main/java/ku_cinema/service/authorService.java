package ku_cinema.service;

import ku_cinema.model.*;
import ku_cinema.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

@Service
public class authorService {

	@Autowired
	authorRepo authorrepo;
	
	@Transactional
	public author FindAuthorUser(String ID) {
		return authorrepo.findByID(ID);
	}
}

package ku_cinema.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import ku_cinema.model.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}

package ku_cinema.service.movie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ku_cinema.model.AvailableMovie;
import ku_cinema.model.AvailableMovieId;
import ku_cinema.model.Movie;
import ku_cinema.repository.movie.AvailableMovieRepo;
import ku_cinema.repository.movie.MovieRepo;

@Service
@Transactional
public class MovieService {
    @Autowired
    AvailableMovieRepo availableMovieRepo;

    @Autowired
    MovieRepo movieRepo;

    public List<AvailableMovie> findAvailableMovies(LocalDate date) {
        return availableMovieRepo
                .findByAvailableMovieId_BookedDateOrderByAvailableMovieId_MovieIdAsc(
                        date);
    }

    public Movie findMovie(Integer movieId) {
        return movieRepo.findOne(movieId);
    }
    
    public AvailableMovie findAvailableMovie(AvailableMovieId availableMovieId) {
    	return availableMovieRepo.findOneForUpdateByAvailableMovieId(availableMovieId);
    }
}
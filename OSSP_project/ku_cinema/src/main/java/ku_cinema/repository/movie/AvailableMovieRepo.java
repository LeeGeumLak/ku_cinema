package ku_cinema.repository.movie;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ku_cinema.model.AvailableMovie;
import ku_cinema.model.AvailableMovieId;

public interface AvailableMovieRepo extends JpaRepository<AvailableMovie, AvailableMovieId>{
	@Query("SELECT DISTINCT x FROM AvailableMovie x "
            + "LEFT JOIN FETCH x.movie "
            + "WHERE x.availableMovieId.availableDate = :date "
            + "ORDER BY x.availableMovieId.movieId ASC")
    List<AvailableMovie> findByAvailableMovieId_BookedDateOrderByAvailableMovieId_MovieIdAsc(
            @Param("date") LocalDate bookedDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    AvailableMovie findOneForUpdateByAvailableMovieId(
            AvailableMovieId availableMovieId);
}

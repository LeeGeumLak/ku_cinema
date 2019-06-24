package ku_cinema.service.booking;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ku_cinema.model.AvailableMovie;
import ku_cinema.model.AvailableMovieId;
import ku_cinema.model.Booking;
import ku_cinema.model.RoleName;
import ku_cinema.model.customer;
import ku_cinema.repository.booking.BookingRepo;
import ku_cinema.repository.movie.AvailableMovieRepo;

@Service
@Transactional
public class BookingService {
    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    AvailableMovieRepo availableMovieRepo;

    public List<Booking> findBookings(
            AvailableMovieId availableMovieId) {
        return bookingRepo
                .findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(
                        availableMovieId);
    }

    public Booking book(Booking booking) {
        AvailableMovieId availableMovieId = booking.getAvailableMovie()
                .getAvailableMovieId();

        // 비관적 락
        AvailableMovie available = availableMovieRepo
                .findOneForUpdateByAvailableMovieId(availableMovieId);
        if (available == null) {
            throw new UnavailableBookingException(
                    "선택한 날짜의 영화는 예매할 수 없습니다.");
        }
        // 예매 가능 여부 확인
        List<Booking> exist_bookings = bookingRepo
                .findByAvailableMovie_AvailableMovieIdOrderByBookedDateAsc(availableMovieId);
        boolean overbook = false;
        if (exist_bookings.size() == available.getSeats()) {
        	overbook = true;
        }
        if (overbook) {
            throw new AlreadyBookedException("해당 영화의 예매 가능한 빈 좌석이 없습니다.");
        }
        // 예약 정보 등록
        bookingRepo.save(booking);
        return booking;
    }

    public void cancel(Integer bookingId, customer requestcustomer) {
        Booking booking = bookingRepo.findOne(bookingId);
        if (RoleName.ADMIN != requestcustomer.getRoleName()
                && !Objects.equals(booking.getUser().getID(),
                        requestcustomer.getID())) {
            throw new AccessDeniedException("예약을 취소할 수 없습니다.");
        }
        bookingRepo.delete(booking);
    }

    @PreAuthorize("hasRole('ADMIN')"
            + " or #booking.user.userId == principal.user.userId")
    public void cancel(@P("booking") Booking booking) {
        bookingRepo.delete(booking);
    }

    public Booking findOne(Integer bookingId) {
        return bookingRepo.findOne(bookingId);
    }

}
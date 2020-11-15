package restaurants.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restaurants.dao.ReservationRepository;
import restaurants.exception.NotFoundException;
import restaurants.exception.ReservationException;
import restaurants.model.Reservation;

@Component
public class ReservationComponentImpl implements ReservationComponent {

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation getReservation(int reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("reservationId:" + reservationId));
	}

	public Reservation save(Reservation reservation) {
		
		if(reservation.getStart().isBefore(LocalDateTime.now())) {
			throw new ReservationException("Reservation in the past!");
		}
		
		if(reservation.getEnd().isBefore(reservation.getStart())) {
			throw new ReservationException("Reservation back-to-front!");
		}
		
		reservationRepository.findByTableIdAndDateRange(reservation.getTableId(), reservation.getStart(),
				reservation.getEnd()).ifPresent(r->{throw new ReservationException("Reservation clash!");});
		
		reservationRepository.save(reservation);
		return reservation;
	}

	public List<Reservation> findByIdDate(int restaurantId, LocalDateTime start, LocalDateTime end) {
		List<Reservation> reservations = reservationRepository.findByRestaurantIdAndDateRange(restaurantId, start, end)
				.orElseThrow(() -> new NotFoundException("No reservations found for restaurantId:" + restaurantId+" and start:"+start+" and end:"+end));
		return reservations;
	}

	public void delete(int reservationId) {
		reservationRepository.deleteById(reservationId);
	}
}

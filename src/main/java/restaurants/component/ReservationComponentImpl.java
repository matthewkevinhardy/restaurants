package restaurants.component;

import java.time.LocalDate;
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

		if (reservation.getStart().isBefore(LocalDateTime.now())) {
			throw new ReservationException("Reservation in the past, start: " + reservation.getStart());
		}

		if (reservation.getEnd().isBefore(reservation.getStart())
				|| reservation.getEnd().isEqual(reservation.getStart())) {
			throw new ReservationException("Start time needs to be before end time");
		}

		reservationRepository.findByTableIdAndDateRange(reservation.getTableId(), reservation.getStart(),
				reservation.getEnd()).ifPresent(r -> {
					throw new ReservationException("Reservation clash!");
				});

		return reservationRepository.save(reservation);
	}

	public Reservation update(Reservation reservation) {

		if (reservation.getStart().isBefore(LocalDateTime.now())) {
			throw new ReservationException("Reservation in the past, start: " + reservation.getStart());
		}

		if (reservation.getEnd().isBefore(reservation.getStart())) {
			throw new ReservationException("Reservation back-to-front!");
		}

		reservationRepository.findByReservationId(reservation.getReservationId())
				.orElseThrow(() -> new NotFoundException("Reservation: " + reservation.getReservationId()));

		List<Reservation> clashList = reservationRepository.findByTableIdAndDateRange(reservation.getTableId(),
				reservation.getStart(), reservation.getEnd()).get();

		if (clashList.isEmpty()) {
			// No clashes
		} else if (clashList.size() == 1 && clashList.contains(reservation)) {
			// Only clash is the record we want to update
		} else {
			// Clashes
			throw new ReservationException("Reservation clash!");
		}

		return reservationRepository.save(reservation);
	}

	public List<Reservation> findByRestaurantIdAndDateRange(int restaurantId, LocalDateTime start, LocalDateTime end) {
		List<Reservation> reservationList = reservationRepository
				.findByRestaurantIdAndDateRange(restaurantId, start, end)
				.orElseThrow(() -> new NotFoundException("No reservations found for restaurantId:" + restaurantId
						+ " and start:" + start + " and end:" + end));

		return reservationList;
	}

	public List<Reservation> findByRestaurantIdAndDate(int restaurantId, LocalDate date) {
		
		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = start.plusDays(1);
		
		List<Reservation> reservationList = reservationRepository
				.findByRestaurantIdAndDateRange(restaurantId,start,end)
				.orElseThrow(() -> new NotFoundException("No reservations found for restaurantId:" + restaurantId
						+ " and date:" + date));

		return reservationList;
	}
	
	public void delete(int reservationId) {
		reservationRepository.findByReservationId(reservationId)
				.orElseThrow(() -> new NotFoundException("Reservation: " + reservationId));

		reservationRepository.deleteById(reservationId);
	}
}

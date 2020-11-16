package restaurants.component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restaurants.dao.ReservationRepository;
import restaurants.dto.ReservationDTO;
import restaurants.exception.NotFoundException;
import restaurants.exception.ReservationException;
import restaurants.model.Reservation;

@Component
public class ReservationComponentImpl implements ReservationComponent {

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation getReservation(int reservationId) {
		ReservationDTO res = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("reservationId:" + reservationId));

		return Reservation.valueOf(res);
	}

	public Reservation save(Reservation reservation) {

		ReservationDTO reservationDTO = ReservationDTO.valueOf(reservation);

		if (reservationDTO.getStart().isBefore(LocalDateTime.now())) {
			throw new ReservationException("Reservation in the past, start: " + reservation.getStart());
		}

		if (reservationDTO.getEnd().isBefore(reservationDTO.getStart())
				|| reservationDTO.getEnd().isEqual(reservationDTO.getStart())) {
			throw new ReservationException("Start time needs to be before end time");
		}

		reservationRepository.findByTableIdAndDateRange(reservationDTO.getTableId(), reservationDTO.getStart(),
				reservationDTO.getEnd()).ifPresent(r -> {
					throw new ReservationException("Reservation clash!");
				});

		ReservationDTO savedDTO = reservationRepository.save(reservationDTO);
		return Reservation.valueOf(savedDTO);
	}

	public Reservation update(Reservation reservation) {

		ReservationDTO reservationDTO = ReservationDTO.valueOf(reservation);

		if (reservationDTO.getStart().isBefore(LocalDateTime.now())) {
			throw new ReservationException("Reservation in the past, start: " + reservation.getStart());
		}

		if (reservationDTO.getEnd().isBefore(reservationDTO.getStart())) {
			throw new ReservationException("Reservation back-to-front!");
		}

		List<ReservationDTO> clashList = reservationRepository.findByTableIdAndDateRange(reservationDTO.getTableId(),
				reservationDTO.getStart(), reservationDTO.getEnd()).get();

		if (clashList.isEmpty()) {
			// No clashes
		} else if (clashList.size() == 1 && clashList.contains(reservationDTO)) {
			// Only clash is the record we want to update
		} else {
			// Clashes
			throw new ReservationException("Reservation clash!");
		}

		ReservationDTO updated = reservationRepository.save(reservationDTO);
		return Reservation.valueOf(updated);
	}

	public List<Reservation> findByIdDate(int restaurantId, LocalDateTime start, LocalDateTime end) {
		List<ReservationDTO> reservationDTOList = reservationRepository
				.findByRestaurantIdAndDateRange(restaurantId, start, end)
				.orElseThrow(() -> new NotFoundException("No reservations found for restaurantId:" + restaurantId
						+ " and start:" + start + " and end:" + end));

		List<Reservation> reservationList = reservationDTOList.stream().map(Reservation::new)
				.collect(Collectors.toCollection(ArrayList::new));
		return reservationList;
	}

	public void delete(int reservationId) {
		reservationRepository.deleteById(reservationId);
	}
}
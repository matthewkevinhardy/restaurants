package restaurants.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import restaurants.model.Reservation;

public interface ReservationComponent {
	public Reservation getReservation(int reservationId);

	public Reservation save(Reservation reservation);

	public Reservation update(Reservation reservation);
	
	public List<Reservation> findByRestaurantIdAndDateRange(int restaurantId,LocalDateTime start,LocalDateTime end);
	
	public List<Reservation> findByRestaurantIdAndDate(int restaurantId,LocalDate date);
	
	public void delete(int reservationId);
}

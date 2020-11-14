package restaurants.utils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Predicate;

import restaurants.model.Reservation;

public class ReservationPredicates {
	public static Predicate<Reservation> byDateAndRestaurant(LocalDateTime start,LocalDateTime end) {
		return r->r.getStart().isAfter(start)&&r.getEnd().isBefore(end);
	}
}

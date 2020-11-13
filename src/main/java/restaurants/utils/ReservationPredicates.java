package restaurants.utils;

import java.util.Date;
import java.util.function.Predicate;

import restaurants.model.Reservation;

public class ReservationPredicates {
	public static Predicate<Reservation> byDateAndRestaurant(Date start,Date end) {
		return r->r.getStart().after(start)&&r.getEnd().before(end);
	}
}

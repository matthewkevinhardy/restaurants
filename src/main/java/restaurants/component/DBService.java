package restaurants.component;

import java.util.Date;
import java.util.List;

import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.Table;

public interface DBService {
	public Restaurant getRestaurant(int id);
	
	public Table getTable(int id);
	public List<Table> getTables(int restaurantId);
	
	public Reservation getReservation(int id);
	public List<Reservation> getReservations(int restaurantId,Date start,Date end);
	
	public int makeReservation(int tableId,int restaurantId,Date start,Date end);
}

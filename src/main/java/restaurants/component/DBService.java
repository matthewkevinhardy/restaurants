package restaurants.component;

import java.util.Date;
import java.util.List;

import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

public interface DBService {
	public Restaurant createRestaurant();
	public Restaurant getRestaurant(int id);
	
	public RestaurantTable createTable(int restaurantId,int capacity);
	public RestaurantTable getTable(int id);
	public List<RestaurantTable> getTables(int restaurantId);
	
	public Reservation getReservation(int id);
	public List<Reservation> getReservations(int restaurantId,Date start,Date end);
	
	public int makeReservation(int tableId,int restaurantId,Date start,Date end);
}

package restaurants.model;

import java.util.List;

public class Restaurant {
	private int restaurantId;
	private List<Table> tables;
	private List<Reservation> reservations;
	
	public Restaurant(int id) {
		this.restaurantId = id;
	}
	
	public List<Table> getTables() {
		return tables;
	}
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}

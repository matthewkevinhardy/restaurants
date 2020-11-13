package restaurants.model;

import java.util.Date;

public class Reservation {
	private int tableId;
	private int restaurantId;
	private Date start;
	private Date end;
	
	public Reservation(int tableId, int restaurantId, Date start, Date end) {
		this.tableId = tableId;
		this.restaurantId = restaurantId;
		this.start = start;
		this.end = end;
	}
	public int getTableId() {
		return tableId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	
	
}

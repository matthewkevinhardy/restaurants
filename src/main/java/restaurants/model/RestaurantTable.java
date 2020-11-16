package restaurants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESTAURANT_TABLE")
public class RestaurantTable {
	
	
	@Id
	@Column(name = "TABLE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tableId;
	
	@Column(name = "SEATING_CAPACITY")
	@NotNull(message = "seatingCapacity is mandatory")
	private int seatingCapacity;
	
	@Column(name = "RESTAURANT_ID")
	@NotNull(message = "restaurantId is mandatory")
	private int restaurantId;
	
	//@ManyToOne
	//private Restaurant restaurant;
	
	public RestaurantTable(int seatingCapacity,int restaurantId) {
		this.seatingCapacity = seatingCapacity;
		this.restaurantId=restaurantId;
	}
	
	public RestaurantTable() {
	}
	
	public int getTableId() {
		return tableId;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
//	public Restaurant getRestaurant() {
//		return restaurant;
//	}
//	public void setRestaurant(Restaurant restaurant) {
//		this.restaurant = restaurant;
//	}
	
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
		
}

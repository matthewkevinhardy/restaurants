package restaurants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private Integer seatingCapacity;
	
	@Column(name = "RESTAURANT_ID")
	@NotNull(message = "restaurantId is mandatory")
	private Integer restaurantId;
	
	public RestaurantTable(Integer seatingCapacity,Integer restaurantId) {
		this.seatingCapacity = seatingCapacity;
		this.restaurantId=restaurantId;
	}
	
	public RestaurantTable() {
	}
	
	public void setTableId(Integer tableId) {
		this.tableId=tableId;
	}
	
	public Integer getTableId() {
		return tableId;
	}
	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}
	
	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
		
}

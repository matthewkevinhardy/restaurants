package restaurants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
	
	@Id
	@Column(name = "RESTAURANT_ID")
	@GeneratedValue
	private int restaurantId;
	
	@Column(name = "RESTAURANT_NAME")
	private String name;
	
	public Restaurant() {
	}
	
	public Restaurant(String name) {
		this.name = name;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

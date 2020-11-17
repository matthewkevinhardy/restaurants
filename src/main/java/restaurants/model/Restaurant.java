package restaurants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
	
	@Id
	@Column(name = "RESTAURANT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer restaurantId;
	
	@NotBlank(message = "name is mandatory")
	@Column(name = "RESTAURANT_NAME")
	private String name;
	
	public Restaurant() {
	}
	
	public Restaurant(String name) {
		this.name=name;
	}
	
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId=restaurantId;
	}
	
	public Integer getRestaurantId() {
		return restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

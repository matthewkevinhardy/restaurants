package restaurants.component;

import java.util.List;

import restaurants.model.Restaurant;

public interface RestaurantComponent {

	public Restaurant getRestaurant(int id);

	public List<Restaurant> getRestaurants();

	public Restaurant save(String restaurantName);

	public Restaurant update(Restaurant restaurant);

	public void delete(int id);
}

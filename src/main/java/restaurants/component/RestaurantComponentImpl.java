package restaurants.component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restaurants.dao.RestaurantRepository;
import restaurants.exception.NotFoundException;
import restaurants.exception.RestaurantException;
import restaurants.model.Restaurant;

@Component
public class RestaurantComponentImpl implements RestaurantComponent {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public Restaurant getRestaurant(int id) {
		return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant:" + id));
	}

	public List<Restaurant> getRestaurants() {
		List<Restaurant> resList = new LinkedList<Restaurant>();
		restaurantRepository.findAll().forEach(resList::add);

		if (resList.isEmpty())
			throw new NotFoundException("No restaurants found");

		return resList;
	}

	public Restaurant save(String restaurantName) {
		restaurantRepository.findByName(restaurantName).ifPresent(r -> {
			throw new RestaurantException("Name already exists: " + r.getName());
		});

		Restaurant saved = restaurantRepository.save(new Restaurant(restaurantName));
		return saved;
	}

	public Restaurant update(Restaurant restaurant) {
		Restaurant currentRecord = restaurantRepository.findByRestaurantId(restaurant.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("Restaurant:" + restaurant.getRestaurantId()));

		Optional<Restaurant> existingName = restaurantRepository.findByName(restaurant.getName());

		if (existingName.isPresent() && !existingName.get().getRestaurantId().equals(currentRecord.getRestaurantId())) {
			throw new RestaurantException("Restaurant name already in use: " + restaurant.getName());
		}

		Restaurant saved = restaurantRepository.save(restaurant);
		return saved;
	}

	public void delete(int id) {
		restaurantRepository.findByRestaurantId(id).orElseThrow(() -> new NotFoundException("Restaurant:" + id));

		restaurantRepository.deleteById(id);
	}

}

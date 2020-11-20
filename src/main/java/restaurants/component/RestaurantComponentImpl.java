package restaurants.component;

import java.util.LinkedList;
import java.util.List;

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
		
		if(resList.isEmpty()) throw new NotFoundException("No restaurants found");
		
		return resList;
	}

	public Restaurant save(Restaurant restaurant) {
		Restaurant saved = restaurantRepository.save(restaurant);
		return saved;
	}

	public Restaurant update(Restaurant restaurant) {
		Restaurant existing = restaurantRepository.findByRestaurantId(restaurant.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("Restaurant:" + restaurant.getRestaurantId()));
		
		if(existing.getName().equals(restaurant.getName())) {
			throw new RestaurantException("Restaurant name already in use: "+restaurant.getName());
		}
		
		Restaurant saved = restaurantRepository.save(restaurant);
		return saved;
	}

	public void delete(int id) {
		restaurantRepository.findByRestaurantId(id).orElseThrow(() -> new NotFoundException("Restaurant:" + id));

		restaurantRepository.deleteById(id);
	}

}

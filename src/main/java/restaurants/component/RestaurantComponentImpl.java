package restaurants.component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import restaurants.dao.RestaurantRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Reservation;
import restaurants.model.Restaurant;

@Component
public class RestaurantComponentImpl implements RestaurantComponent {

	@Autowired
    private RestaurantRepository restaurantRepository;
	
	public Restaurant getRestaurant(int id) {
		return restaurantRepository.findById(id).orElseThrow(()->new NotFoundException("Restaurant:"+id));
	}
	
	public List<Restaurant> getRestaurants() {
		List<Restaurant> resList = new LinkedList<Restaurant>();
		restaurantRepository.findAll().forEach(resList::add);
		return resList;
	}
	
	public Restaurant save(Restaurant restaurant) {
		Restaurant saved = restaurantRepository.save(restaurant);
		return saved;
	}
	
	public Restaurant update(Restaurant restaurant) {
		Restaurant saved = restaurantRepository.save(restaurant);
		return saved;
	}
	
	public void delete(int id) {
		restaurantRepository.deleteById(id);
	}

}

package restaurants.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.component.RestaurantComponent;
import restaurants.model.Restaurant;

@RestController
@RequestMapping("restaurant_app")
public class RestaurantController {
	
	@Autowired
    private RestaurantComponent restaurantComponent;
	
	@ApiOperation(value = "Get a restaurant")
	@GetMapping(path = "/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Restaurant getRestaurant(@PathVariable(value = "id",required = true) Integer id) {
		return restaurantComponent.getRestaurant(id);
	}
	
	@ApiOperation(value = "Get all restaurants")
	@GetMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant> getRestaurants() {
		return restaurantComponent.getRestaurants();
	}
	
	@ApiOperation(value = "Add a restaurant")
	@PostMapping(path = "/restaurant/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Restaurant save(@Valid @RequestBody Restaurant restaurant) {
		Restaurant saved = restaurantComponent.save(restaurant);
		return saved;
	}
	
	@ApiOperation(value = "Update a restaurant")
	@PutMapping(path = "/restaurant/{restaurantId}/update")
	public Restaurant update(@PathVariable(value = "restaurantId",required = true) Integer restaurantId,
			@Valid @RequestBody Restaurant restaurant) {
		restaurant.setRestaurantId(restaurantId);
		Restaurant saved = restaurantComponent.update(restaurant);
		return saved;
	}
	
	@ApiOperation(value = "Delete a restaurant")
	@DeleteMapping(path = "/restaurant/{id}")
	public void delete(@PathVariable(value = "id", required = true) int id) {
		restaurantComponent.delete(id);
	}
}

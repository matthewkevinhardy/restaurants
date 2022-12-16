package restaurants.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.component.RestaurantComponent;
import restaurants.model.Restaurant;

@RestController
@RequestMapping("/api/v1")
public class RestaurantController {

	@Autowired
	private RestaurantComponent restaurantComponent;

	@ApiOperation(value = "Get a restaurant")
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable(value = "id", required = true) Integer id) {
		return new ResponseEntity<>(restaurantComponent.getRestaurant(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all restaurants")
	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getRestaurants() {
		return new ResponseEntity<>(restaurantComponent.getRestaurants(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a restaurant")
	@PostMapping("/restaurant/save")
	public ResponseEntity<Restaurant> save(@Valid @RequestBody Restaurant restaurant) {
		Restaurant saved = restaurantComponent.save(restaurant.getName());
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a restaurant")
	@PutMapping("/restaurant/{restaurantId}/update")
	public ResponseEntity<Restaurant> update(
			@PathVariable(value = "restaurantId", required = true) Integer restaurantId,
			@Valid @RequestBody Restaurant restaurant) {
		restaurant.setRestaurantId(restaurantId);
		Restaurant saved = restaurantComponent.update(restaurant);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a restaurant")
	@DeleteMapping("/restaurant/{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) int id) {
		restaurantComponent.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

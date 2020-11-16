package restaurants.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.dao.RestaurantRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@RestController
@RequestMapping("restaurant_app")
public class RestaurantController {
	
	@Autowired
    private RestaurantController restaurantController;
	
	@ApiOperation(value = "Get a restaurant")
	@GetMapping(path = "/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Restaurant getRestaurant(@PathVariable(value = "id",required = true) Integer id) {
		return restaurantController.getRestaurant(id);
	}
	
	@ApiOperation(value = "Get all restaurants")
	@GetMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant> getRestaurants() {
		return restaurantController.getRestaurants();
	}
	
	@ApiOperation(value = "Add a restaurant")
	@PostMapping(path = "/restaurant/save")
	public Restaurant save(@Valid @RequestBody Restaurant restaurant) {
		Restaurant saved = restaurantController.save(restaurant);
		return saved;
	}
	
	@ApiOperation(value = "Update a restaurant")
	@PutMapping(path = "/restaurant/update")
	public Restaurant update(@Valid @RequestBody Restaurant restaurant) {
		Restaurant saved = restaurantController.save(restaurant);
		return saved;
	}
	
	@ApiOperation(value = "Delete a restaurant")
	@DeleteMapping(path = "/restaurant/{id}")
	public void delete(@PathVariable(value = "id", required = true) int id) {
		restaurantController.delete(id);
	}
}

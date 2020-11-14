package restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.dao.RestaurantRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Restaurant;

@RestController
@RequestMapping("restaurant_app")
public class RestaurantController {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	
	@ApiOperation(value = "Get a restaurant")
	@GetMapping(path = "/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Restaurant getRestaurant(@PathVariable(value = "id",required = true) Integer id) {
		return restaurantRepository.findById(id).orElseThrow(()->new NotFoundException("Restaurant:"+id));
	}
	
	@ApiOperation(value = "Add a restaurant")
	@PostMapping(path = "/restaurant/save")
	public Restaurant save(final @RequestBody Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return restaurant;
	}
}

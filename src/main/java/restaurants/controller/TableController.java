package restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.dao.RestaurantRepository;
import restaurants.dao.RestaurantTableRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@RestController
@RequestMapping("restaurant_app")
public class TableController {

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@ApiOperation(value = "Get a table")
	@GetMapping(path = "/table/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantTable getTable(@PathVariable(value = "id", required = true) Integer id) {
		return restaurantTableRepository.findById(id).orElseThrow(() -> new NotFoundException("RestaurantId:" + id));
	}

	@ApiOperation(value = "Add a table")
	@PostMapping(path = "/table/save")
	public int save(final @RequestBody RestaurantTable restaurantTable) {
		restaurantTableRepository.save(restaurantTable);
		return restaurantTable.getTableId();
	}

	@ApiOperation(value = "Get all tables in a restaurant")
	@GetMapping(path = "/restaurant/{restaurantId}/tables", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestaurantTable> getRestaurantTables(
			@PathVariable(value = "restaurantId", required = true) int restaurantId) {
		return restaurantTableRepository.findByRestaurantId(restaurantId)
				.orElseThrow(() -> new NotFoundException("RestaurantId:" + restaurantId));
	}
}

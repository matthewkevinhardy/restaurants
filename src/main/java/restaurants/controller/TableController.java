package restaurants.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.component.TableComponentImpl;
import restaurants.dao.RestaurantRepository;
import restaurants.dao.RestaurantTableRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@RestController
@RequestMapping("restaurant_app")
public class TableController {

	@Autowired
	private TableComponentImpl tableComponent;

	@ApiOperation(value = "Get a table")
	@GetMapping(path = "/table/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestaurantTable getTable(@PathVariable(value = "id", required = true) Integer id) {
		return tableComponent.getTable(id);
	}

	@ApiOperation(value = "Add a table")
	@PostMapping(path = "/table/save")
	public RestaurantTable save(final @RequestBody RestaurantTable restaurantTable) {
		RestaurantTable saved = tableComponent.save(restaurantTable);
		return saved;
	}

	@ApiOperation(value = "Get all tables in a restaurant")
	@GetMapping(path = "/restaurant/{restaurantId}/tables", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestaurantTable> getRestaurantTables(
			@PathVariable(value = "restaurantId", required = true) int restaurantId) {
		return tableComponent.getRestaurantTables(restaurantId);
	}
	
	@ApiOperation(value = "Get free tables in a restaurant for date range")
	@GetMapping(path = "/restaurant/{restaurantId}/freeTables", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestaurantTable> findFreeTables(
			@PathVariable(value = "restaurantId", required = true) int restaurantId,
			@RequestParam(name = "seatingCapacity", required = true) int seatingCapacity,
			@RequestParam(name = "startTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
			@RequestParam(name = "endTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime ) {
		return tableComponent.findFreeTables(restaurantId, seatingCapacity, startTime, endTime);
	}
	
	@ApiOperation(value = "Delete a table")
	@DeleteMapping(path = "/table/{id}")
	public void delete(@PathVariable(value = "id", required = true) int id) {
		tableComponent.delete(id);
	}
	
	@ApiOperation(value = "Update a table")
	@PutMapping(path = "/table/update")
	public RestaurantTable update(final @RequestBody RestaurantTable restaurantTable) {
		RestaurantTable saved = tableComponent.update(restaurantTable);
		return saved;
	}
}

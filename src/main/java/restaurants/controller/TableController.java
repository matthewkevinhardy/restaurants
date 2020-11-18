package restaurants.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import restaurants.model.RestaurantTable;

@RestController
@RequestMapping("/api/v1")
public class TableController {

	@Autowired
	private TableComponentImpl tableComponent;

	@ApiOperation(value = "Get a table")
	@GetMapping("/table/{id}")
	public ResponseEntity<RestaurantTable> getTable(@PathVariable(value = "id", required = true) Integer id) {
		return new ResponseEntity<>(tableComponent.getTable(id),HttpStatus.OK);
	}

	@ApiOperation(value = "Add a table")
	@PostMapping("/table/save")
	public ResponseEntity<RestaurantTable> save(@Valid @RequestBody RestaurantTable restaurantTable) {
		RestaurantTable saved = tableComponent.save(restaurantTable);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all tables in a restaurant")
	@GetMapping("/restaurant/{restaurantId}/tables")
	public ResponseEntity<List<RestaurantTable>> getRestaurantTables(
			@PathVariable(value = "restaurantId", required = true) int restaurantId) {
		return new ResponseEntity<>(tableComponent.getRestaurantTables(restaurantId),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get free tables in a restaurant for date range")
	@GetMapping("/restaurant/{restaurantId}/freeTables")
	public ResponseEntity<List<RestaurantTable>> findFreeTables(
			@PathVariable(value = "restaurantId", required = true) int restaurantId,
			@RequestParam(name = "seatingCapacity", required = true) int seatingCapacity,
			@RequestParam(name = "startTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
			@RequestParam(name = "endTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime ) {
		return new ResponseEntity<>(tableComponent.findFreeTables(restaurantId, seatingCapacity, startTime, endTime),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete a table")
	@DeleteMapping("/table/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) int id) {
		tableComponent.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Update a table")
	@PutMapping("/table/{tableId}/update")
	public ResponseEntity<RestaurantTable> update(@Valid @RequestBody RestaurantTable restaurantTable,@PathVariable(value = "tableId", required = true) Integer tableId) {
		restaurantTable.setTableId(tableId);
		RestaurantTable saved = tableComponent.update(restaurantTable);
		return new ResponseEntity<>(saved,HttpStatus.OK);
	}
}

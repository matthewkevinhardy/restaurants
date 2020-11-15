package restaurants.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restaurants.dao.RestaurantTableRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.RestaurantTable;

@Component
public class TableComponentImpl implements TableComponent {
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	
	public RestaurantTable getTable(int id) {
		return restaurantTableRepository.findById(id).orElseThrow(() -> new NotFoundException("Table id:" + id));
	}

	public RestaurantTable save(RestaurantTable restaurantTable) {
		RestaurantTable saved = restaurantTableRepository.save(restaurantTable);
		return saved;
	}

	public List<RestaurantTable> getRestaurantTables(int restaurantId) {
		return restaurantTableRepository.findByRestaurantId(restaurantId)
				.orElseThrow(() -> new NotFoundException("RestaurantId:" + restaurantId));
	}
	
	public List<RestaurantTable> findFreeTables(int restaurantId,int seatingCapacity,LocalDateTime startTime,LocalDateTime endTime) {
		return restaurantTableRepository.findFreeTables(restaurantId, seatingCapacity, startTime, endTime)
				.orElseThrow(() -> new NotFoundException("No free tables:" + restaurantId));
	}
	
	public void delete(int id) {
		restaurantTableRepository.deleteById(id);
	}
	
	public RestaurantTable update(RestaurantTable restaurantTable) {
		RestaurantTable saved = restaurantTableRepository.save(restaurantTable);
		return saved;
	}
}

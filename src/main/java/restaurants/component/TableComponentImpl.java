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

	public RestaurantTable getTable(Integer id) {
		return restaurantTableRepository.findById(id).orElseThrow(() -> new NotFoundException("Table id:" + id));
	}

	public RestaurantTable save(RestaurantTable restaurantTable) {
		RestaurantTable saved = restaurantTableRepository.save(restaurantTable);
		return saved;
	}

	public List<RestaurantTable> getRestaurantTables(Integer restaurantId) {
		return restaurantTableRepository.findByRestaurantId(restaurantId)
				.orElseThrow(() -> new NotFoundException("RestaurantId:" + restaurantId));
	}

	public List<RestaurantTable> findFreeTables(Integer restaurantId, Integer seatingCapacity, LocalDateTime startTime,
			LocalDateTime endTime) {
		return restaurantTableRepository.findFreeTables(restaurantId, seatingCapacity, startTime, endTime)
				.orElseThrow(() -> new NotFoundException("No free tables:" + restaurantId));
	}

	public void delete(Integer id) {
		restaurantTableRepository.findById(id).orElseThrow(() -> new NotFoundException("Table:" + id));

		restaurantTableRepository.deleteById(id);
	}

	public RestaurantTable update(RestaurantTable restaurantTable) {
		restaurantTableRepository.findByRestaurantId(restaurantTable.getTableId())
				.orElseThrow(() -> new NotFoundException("Table:" + restaurantTable.getTableId()));
		RestaurantTable saved = restaurantTableRepository.save(restaurantTable);
		return saved;
	}
}

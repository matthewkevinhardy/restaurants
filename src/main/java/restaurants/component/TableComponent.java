package restaurants.component;

import java.time.LocalDateTime;
import java.util.List;

import restaurants.model.RestaurantTable;

public interface TableComponent {
	
	public RestaurantTable getTable(Integer id);

	public RestaurantTable save(RestaurantTable restaurantTable);

	public List<RestaurantTable> getRestaurantTables(Integer restaurantId);
	
	public List<RestaurantTable> findFreeTables(Integer restaurantId,Integer seatingCapacity,LocalDateTime startTime,LocalDateTime endTime);
	
	public void delete(Integer id);
	
	public RestaurantTable update(RestaurantTable restaurantTable);
}

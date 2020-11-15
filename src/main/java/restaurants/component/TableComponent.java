package restaurants.component;

import java.time.LocalDateTime;
import java.util.List;

import restaurants.model.RestaurantTable;

public interface TableComponent {
	
	public RestaurantTable getTable(int id);

	public RestaurantTable save(RestaurantTable restaurantTable);

	public List<RestaurantTable> getRestaurantTables(int restaurantId);
	
	public List<RestaurantTable> findFreeTables(int restaurantId,int seatingCapacity,LocalDateTime startTime,LocalDateTime endTime);
	
	public void delete(int id);
	
	public RestaurantTable update(RestaurantTable restaurantTable);
}

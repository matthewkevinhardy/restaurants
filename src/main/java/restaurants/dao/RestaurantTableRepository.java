package restaurants.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restaurants.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {
	Optional<RestaurantTable> findByTableId(Integer tableId);

	@Query("SELECT t FROM RestaurantTable t WHERE t.restaurantId=:restaurantId")
	public Optional<List<RestaurantTable>> findByRestaurantId(@Param("restaurantId") int restaurantId);

	@Query("SELECT t " + 
			"from RestaurantTable  t " + 
			"where t.restaurantId=:restaurantId " + 
			"and t.seatingCapacity=:seatingCapacity " + 
			"and t.tableId not in(" + 
			"SELECT t.tableId " + 
			"FROM Reservation r, RestaurantTable t " + 
			"where r.tableId=t.tableId " + 
			"and t.restaurantId=:restaurantId " + 
			"and t.seatingCapacity=:seatingCapacity " + 
			"and r.start<:endTime " + 
			"and r.end>:startTime)")
	public Optional<List<RestaurantTable>> findFreeTables(@Param("restaurantId") int restaurantId, 
			int seatingCapacity, LocalDateTime startTime, LocalDateTime endTime);
}

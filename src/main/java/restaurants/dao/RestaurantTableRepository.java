package restaurants.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restaurants.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {
	RestaurantTable findById(int id);
	
	@Query("SELECT t FROM RestaurantTable t WHERE t.restaurantId=:restaurantId")
	public Optional<List<RestaurantTable>> findByRestaurantId(@Param("restaurantId") int restaurantId);
}

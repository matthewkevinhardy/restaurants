package restaurants.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restaurants.model.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
	Optional<Restaurant> findByRestaurantId(int id);
	
	@Query("SELECT r FROM Restaurant r WHERE r.name=:name")
	public Optional<Restaurant> findByName(@Param("name") String name);
}

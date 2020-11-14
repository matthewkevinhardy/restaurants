package restaurants.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restaurants.model.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	public Optional<Reservation> findById(int id);

	@Query("SELECT rvn FROM Reservation rvn,RestaurantTable tab "
			+ "WHERE rvn.tableId=tab.tableId AND tab.restaurantId=:restaurantId "
			+ "AND rvn.start>:start AND rvn.end<:end")
	public Optional<List<Reservation>> findByRestaurantIdAndDate(@Param("restaurantId") int restaurantId,
			@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}

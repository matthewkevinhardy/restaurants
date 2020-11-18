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
	public Optional<Reservation> findByReservationId(Integer id);

	@Query("SELECT rvn FROM ReservationDTO rvn,RestaurantTable tab "
			+ "WHERE rvn.tableId=tab.tableId AND tab.restaurantId=:restaurantId "
			+ "AND rvn.start<:end AND rvn.end>:start")
	public Optional<List<Reservation>> findByRestaurantIdAndDateRange(@Param("restaurantId") int restaurantId,
			@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	@Query("SELECT rvn FROM ReservationDTO rvn "
			+ "WHERE rvn.tableId=:tableId "
			+ "AND rvn.start<:end AND rvn.end>:start")
	public Optional<List<Reservation>> findByTableIdAndDateRange(@Param("tableId") int tableId,
			@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}

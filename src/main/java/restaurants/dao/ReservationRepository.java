package restaurants.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import restaurants.dto.ReservationDTO;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationDTO, Integer> {
	public Optional<ReservationDTO> findByReservationId(Integer id);

	@Query("SELECT rvn FROM ReservationDTO rvn,RestaurantTable tab "
			+ "WHERE rvn.tableId=tab.tableId AND tab.restaurantId=:restaurantId "
			+ "AND rvn.start<:end AND rvn.end>:start")
	public Optional<List<ReservationDTO>> findByRestaurantIdAndDateRange(@Param("restaurantId") int restaurantId,
			@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	@Query("SELECT rvn FROM ReservationDTO rvn,RestaurantTable tab "
			+ "WHERE rvn.tableId=tab.tableId AND tab.restaurantId=:restaurantId "
			+ "AND rvn.start=:date")
	public Optional<List<ReservationDTO>> findByRestaurantIdAndDate(@Param("restaurantId") int restaurantId,
			@Param("date") LocalDate date);
	
	@Query("SELECT rvn FROM ReservationDTO rvn "
			+ "WHERE rvn.tableId=:tableId "
			+ "AND rvn.start<:end AND rvn.end>:start")
	public Optional<List<ReservationDTO>> findByTableIdAndDateRange(@Param("tableId") int tableId,
			@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}

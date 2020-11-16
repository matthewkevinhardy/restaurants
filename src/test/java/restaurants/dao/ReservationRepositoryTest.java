package restaurants.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import restaurants.dto.ReservationDTO;
import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ReservationRepositoryTest {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@Autowired
	private ReservationRepository reservationRespository;

	private Restaurant insertRestaurant1;
	private RestaurantTable insertTable1;
	private RestaurantTable insertTable2;
	private RestaurantTable insertTable3;

	@BeforeAll
	public void setup() {
		insertRestaurant1 = restaurantRepository.save(new Restaurant("R4"));

		insertTable1 = restaurantTableRepository.save(new RestaurantTable(6, insertRestaurant1.getRestaurantId()));
		insertTable2 = restaurantTableRepository.save(new RestaurantTable(7, insertRestaurant1.getRestaurantId()));
		insertTable3 = restaurantTableRepository.save(new RestaurantTable(8, insertRestaurant1.getRestaurantId()));

	}

	@Test
	void testFindByIdInt() {

		LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
		LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0));

		ReservationDTO r1 = reservationRespository.save(new ReservationDTO(insertTable1.getTableId(), start, end));
		ReservationDTO r2 = reservationRespository.save(new ReservationDTO(insertTable2.getTableId(), start, end));
		ReservationDTO r3 = reservationRespository.save(new ReservationDTO(insertTable3.getTableId(), start, end));

		assertNotNull(r1);
		assertNotNull(r2);
		assertNotNull(r3);

		assertEquals(insertTable1.getTableId(), r1.getTableId());
		assertEquals(insertTable2.getTableId(), r2.getTableId());
		assertEquals(insertTable3.getTableId(), r3.getTableId());
	}

	@Test
	void findByRestaurantIdAndDateTest() {
		LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
		LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));

		List<ReservationDTO> reservations = reservationRespository
				.findByRestaurantIdAndDateRange(insertRestaurant1.getRestaurantId(), start, end).get();

		assertEquals(3, reservations.size());
	}
}

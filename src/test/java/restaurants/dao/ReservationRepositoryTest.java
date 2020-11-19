package restaurants.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	private Restaurant insertRestaurant8;
	private RestaurantTable insertTable1;
	private RestaurantTable insertTable2;
	private RestaurantTable insertTable3;

	@BeforeAll
	public void setup() {
		insertRestaurant8 = restaurantRepository.save(new Restaurant("R8"));

		insertTable1 = restaurantTableRepository.save(new RestaurantTable(6, insertRestaurant8.getRestaurantId()));
		insertTable2 = restaurantTableRepository.save(new RestaurantTable(7, insertRestaurant8.getRestaurantId()));
		insertTable3 = restaurantTableRepository.save(new RestaurantTable(8, insertRestaurant8.getRestaurantId()));

	}

	@Test
	void testFindByIdInt() {

		LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));
		LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0));

		Reservation r1 = reservationRespository.save(new Reservation(insertTable1.getTableId(), start, end));
		Reservation r2 = reservationRespository.save(new Reservation(insertTable2.getTableId(), start, end));
		Reservation r3 = reservationRespository.save(new Reservation(insertTable3.getTableId(), start, end));

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

		List<Reservation> reservations = reservationRespository
				.findByRestaurantIdAndDateRange(insertRestaurant8.getRestaurantId(), start, end).get();

		assertEquals(3, reservations.size());
	}
}

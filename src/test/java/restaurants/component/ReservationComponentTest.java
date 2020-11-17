package restaurants.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import restaurants.exception.NotFoundException;
import restaurants.exception.ReservationException;
import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ReservationComponentTest {

	@Autowired
	private ReservationComponent reservationComponent;

	@Autowired
	private RestaurantComponent restaurantComponent;

	@Autowired
	private TableComponent tableComponent;

	private Restaurant insertRestaurant1;
	private Restaurant insertRestaurant2;
	private RestaurantTable insertTable1;
	private RestaurantTable insertTable2;
	private RestaurantTable insertTable3;
	private RestaurantTable insertTable4;
	private RestaurantTable insertTable5;
	private RestaurantTable insertTable6;

	@BeforeAll
	public void setup() {
		insertRestaurant1 = restaurantComponent.save(new Restaurant("R4"));
		insertRestaurant2 = restaurantComponent.save(new Restaurant("R5"));

		insertTable1 = tableComponent.save(new RestaurantTable(6, insertRestaurant1.getRestaurantId()));
		insertTable2 = tableComponent.save(new RestaurantTable(7, insertRestaurant1.getRestaurantId()));
		insertTable3 = tableComponent.save(new RestaurantTable(8, insertRestaurant1.getRestaurantId()));

		insertTable4 = tableComponent.save(new RestaurantTable(10, insertRestaurant2.getRestaurantId()));
		insertTable5 = tableComponent.save(new RestaurantTable(10, insertRestaurant2.getRestaurantId()));
		insertTable6 = tableComponent.save(new RestaurantTable(11, insertRestaurant2.getRestaurantId()));
	}

	@Test
	void testSave() {
		try {
			LocalDateTime start = LocalDateTime.now().plusHours(1);
			LocalDateTime end = LocalDateTime.now().plusHours(2);

			Reservation saved = reservationComponent.save(new Reservation(insertTable1.getTableId(), start, end));

			assertEquals(start, saved.getStart());
			assertEquals(end, saved.getEnd());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testGetReservation() {
		try {
			LocalDateTime start = LocalDateTime.now().plusHours(1);
			LocalDateTime end = LocalDateTime.now().plusHours(2);

			Reservation inserted = reservationComponent.save(new Reservation(insertTable2.getTableId(), start, end));

			Reservation fromDb = reservationComponent.getReservation(inserted.getReservationId());

			assertEquals(start, fromDb.getStart());
			assertEquals(end, fromDb.getEnd());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testFindByIdDate() {
		try {
			LocalDateTime now = LocalDateTime.now();

			reservationComponent
					.save(new Reservation(insertTable4.getTableId(), now.plusMinutes(10), now.plusMinutes(20)));
			reservationComponent
					.save(new Reservation(insertTable5.getTableId(), now.plusMinutes(30), now.plusMinutes(40)));
			reservationComponent
					.save(new Reservation(insertTable6.getTableId(), now.plusMinutes(50), now.plusMinutes(60)));

			List<Reservation> reservations = reservationComponent.findByRestaurantIdAndDateRange(this.insertRestaurant2.getRestaurantId(),
					now, now.plusMinutes(25));
			assertEquals(1, reservations.size());

			reservations = reservationComponent.findByRestaurantIdAndDateRange(this.insertRestaurant2.getRestaurantId(), now,
					now.plusMinutes(45));
			assertEquals(2, reservations.size());

			reservations = reservationComponent.findByRestaurantIdAndDateRange(this.insertRestaurant2.getRestaurantId(), now,
					now.plusMinutes(65));
			assertEquals(3, reservations.size());

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testDelete() {
		try {
			LocalDateTime now = LocalDateTime.now();

			Reservation saved = reservationComponent
					.save(new Reservation(insertTable3.getTableId(), now.plusMinutes(70), now.plusMinutes(80)));

			reservationComponent.delete(saved.getReservationId());

			assertThrows(NotFoundException.class, () -> reservationComponent.getReservation(saved.getReservationId()));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testPastReservation() {
		try {
			LocalDateTime now = LocalDateTime.now();

			assertThrows(ReservationException.class, () -> reservationComponent
					.save(new Reservation(insertTable4.getTableId(), now.minusMinutes(10), now.plusMinutes(10))));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testOverlappingReservation() {
		try {
			LocalDateTime now = LocalDateTime.now();

			reservationComponent
					.save(new Reservation(insertTable5.getTableId(), now.plusMinutes(10), now.plusMinutes(20)));

			assertThrows(ReservationException.class, () -> reservationComponent
					.save(new Reservation(insertTable5.getTableId(), now.minusMinutes(15), now.plusMinutes(30))));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testInvalidReservation() {
		try {
			LocalDateTime now = LocalDateTime.now();

			assertThrows(ReservationException.class, () -> reservationComponent
					.save(new Reservation(insertTable5.getTableId(), now.plusMinutes(15), now.plusMinutes(10))));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testUpdateReservation() {
		try {
			LocalDateTime now = LocalDateTime.now();

			Reservation saved = reservationComponent
					.save(new Reservation(insertTable6.getTableId(), now.plusMinutes(10), now.plusMinutes(15)));

			LocalDateTime newEnd = now.plusMinutes(30);

			saved.setEnd(newEnd);
			reservationComponent.update(saved);

			assertEquals(newEnd, reservationComponent.getReservation(saved.getReservationId()).getEnd());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testUpdateReservationRecordNotExis() {
		try {
			LocalDateTime now = LocalDateTime.now();

			Reservation updateNotExists = new Reservation(insertTable6.getTableId(), now.plusMinutes(10),
					now.plusMinutes(15));
			updateNotExists.setReservationId(123456);

			assertThrows(NotFoundException.class, () -> reservationComponent.update(updateNotExists));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testInvalidUpdate() {
		try {
			LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

			Reservation saved = reservationComponent.save(
					new Reservation(insertTable6.getTableId(), tomorrow.plusMinutes(10), tomorrow.plusMinutes(20)));
			reservationComponent.save(
					new Reservation(insertTable6.getTableId(), tomorrow.plusMinutes(40), tomorrow.plusMinutes(50)));

			saved.setStart(tomorrow.plusMinutes(30));
			saved.setEnd(tomorrow.plusMinutes(45));
			assertThrows(ReservationException.class, () -> reservationComponent.update(saved));

		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testFindFreeTables() {
		try {
			LocalDateTime nextWeek = LocalDateTime.now().plusWeeks(1);

			reservationComponent.save(
					new Reservation(insertTable4.getTableId(), nextWeek.plusMinutes(10), nextWeek.plusMinutes(20)));
			
			reservationComponent.save(
					new Reservation(insertTable5.getTableId(), nextWeek.plusMinutes(10), nextWeek.plusMinutes(60)));
			
			
			assertThrows(NotFoundException.class, () -> tableComponent.findFreeTables(insertRestaurant2.getRestaurantId(), 10, 
					nextWeek.plusMinutes(10),
					nextWeek.plusMinutes(20)));
			
			List<RestaurantTable> freeTableList = tableComponent.findFreeTables(insertRestaurant2.getRestaurantId(), 10, 
					nextWeek.plusMinutes(25),
					nextWeek.plusMinutes(35));
			assertEquals(1, freeTableList.size());
			
			freeTableList = tableComponent.findFreeTables(insertRestaurant2.getRestaurantId(), 10, 
					nextWeek.plusMinutes(60),
					nextWeek.plusMinutes(70));
			assertEquals(2, freeTableList.size());
			

		} catch (Exception e) {
			fail(e);
		}
	}
}

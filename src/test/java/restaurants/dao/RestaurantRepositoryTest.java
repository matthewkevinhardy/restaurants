package restaurants.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import restaurants.model.Restaurant;

@SpringBootTest
class RestaurantRepositoryTest {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	
	@Test
	void testFindByIdInt() {
		try {
			Restaurant insertRestaurant = restaurantRepository.save(new Restaurant("R1"));
			Restaurant getRestaurant = restaurantRepository.findByRestaurantId(insertRestaurant.getRestaurantId()).get();
			assertNotNull(getRestaurant);
			assertEquals("R1", getRestaurant.getName());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testFindByName() {
		try {
			restaurantRepository.save(new Restaurant("R2"));
			Restaurant getRestaurant = restaurantRepository.findByName("R2").get();
			assertNotNull(getRestaurant);
			assertEquals("R2", getRestaurant.getName());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testUpdate() {
		try {
			restaurantRepository.save(new Restaurant("R3"));
			Restaurant r3 = restaurantRepository.findByName("R3").get();
			r3.setName("R3 Updated Name");
			restaurantRepository.save(r3);
			
			Restaurant r3updated = restaurantRepository.findById(r3.getRestaurantId()).get();
			assertEquals("R3 Updated Name",r3updated.getName());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	void testDelete() {
		restaurantRepository.save(new Restaurant("R4"));
		Restaurant r4 = restaurantRepository.findByName("R4").get();
		restaurantRepository.delete(r4);
		
		assertThrows(NoSuchElementException.class, ()->restaurantRepository.findById(r4.getRestaurantId()).get() );
	}
}

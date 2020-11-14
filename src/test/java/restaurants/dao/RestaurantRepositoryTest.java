package restaurants.dao;

import static org.junit.jupiter.api.Assertions.*;

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
		Restaurant insertRestaurant = restaurantRepository.save(new Restaurant("R1"));
		Restaurant getRestaurant = restaurantRepository.findById(insertRestaurant.getRestaurantId());
		assertNotNull(getRestaurant);
		assertEquals("R1", getRestaurant.getName());
	}

	@Test
	void testFindByName() {
		restaurantRepository.save(new Restaurant("R2"));
		Restaurant getRestaurant = restaurantRepository.findByName("R2");
		assertNotNull(getRestaurant);
		assertEquals("R2", getRestaurant.getName());
	}

}

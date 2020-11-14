package restaurants.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;

@SpringBootTest
class RestaurantTableRepositoryTest {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	
	@Autowired
    private RestaurantTableRepository restaurantTableRepository;
	
	@Test
	void testFindByIdInt() {
		Restaurant insertRestaurant1 = restaurantRepository.save(new Restaurant("R1"));
		Restaurant insertRestaurant2 = restaurantRepository.save(new Restaurant("R2"));
		
		RestaurantTable insertTable1 = restaurantTableRepository.save(new RestaurantTable(6,insertRestaurant1.getRestaurantId()));
		RestaurantTable insertTable2 = restaurantTableRepository.save(new RestaurantTable(6,insertRestaurant2.getRestaurantId()));
		
		RestaurantTable getRestaurantTable1 = restaurantTableRepository.findById(insertTable1.getTableId());
		assertNotNull(getRestaurantTable1);
		assertEquals(insertTable1.getTableId(), getRestaurantTable1.getTableId());
		assertEquals(6, getRestaurantTable1.getSeatingCapacity());
		
		RestaurantTable getRestaurantTable2 = restaurantTableRepository.findById(insertTable2.getTableId());
		assertNotNull(getRestaurantTable2);
		assertEquals(insertTable2.getTableId(), getRestaurantTable2.getTableId());
		assertEquals(6, getRestaurantTable2.getSeatingCapacity());
	}
	
	@Test
	void testFindByRestaurantId() {
		Restaurant insertRestaurant3 = restaurantRepository.save(new Restaurant("R3"));
		RestaurantTable insertTable1 = restaurantTableRepository.save(new RestaurantTable(6,insertRestaurant3.getRestaurantId()));
		RestaurantTable insertTable2 = restaurantTableRepository.save(new RestaurantTable(6,insertRestaurant3.getRestaurantId()));
		
		List<RestaurantTable> tableList = restaurantTableRepository.findByRestaurantId(insertRestaurant3.getRestaurantId()).get();
		assertEquals(2, tableList.size());
	}
}

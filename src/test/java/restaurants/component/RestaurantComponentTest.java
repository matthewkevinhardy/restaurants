package restaurants.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import restaurants.exception.RestaurantException;
import restaurants.model.Restaurant;

@SpringBootTest
class RestaurantComponentTest {

	@Autowired
	private RestaurantComponent restaurantComponent;

	@Test
	void testSave() {
		try {
			Restaurant saved = restaurantComponent.save("Retaurant save test");
			assertEquals("Retaurant save test", saved.getName());

			assertThrows(RestaurantException.class, () -> restaurantComponent.save("Retaurant save test"));

		} catch (Exception e) {
			fail(e);
		}
	}
}

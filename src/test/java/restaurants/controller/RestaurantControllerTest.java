package restaurants.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import restaurants.model.Restaurant;
import restaurants.test.Utils;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class RestaurantControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(0)
	void testGetRestaurantsNotFound() {
		try {
			this.mockMvc.perform(get("/api/v1/restaurants")).andExpect(status().isNotFound());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(1)
	void testSaveNoAuth() {
		try {
			this.mockMvc.perform(post("/api/v1/restaurant/save")
							.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"test\"}"))
					.andExpect(status().isUnauthorized());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(2)
	void testSave() {
		try {
			String token = Utils.obtainAccessToken(this.mockMvc,"sa", "password");
			
			this.mockMvc.perform(post("/api/v1/restaurant/save")
							.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"test\"}")
							.header("Authorization", "Bearer "+token))
					.andExpect(status().isCreated());
			
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(3)
	void testGetRestaurant() {
		try {
			Restaurant newRestaurant = Utils.getAllRestaurants(this.mockMvc).get(0);
			
			this.mockMvc.perform(get("/api/v1/restaurant/"+newRestaurant.getRestaurantId()))
				.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	@Order(4)
	void testUpdateNoAuth() {
		try {
			this.mockMvc.perform(put("/api/v1/restaurant/1/update")
							.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"updated\"}"))
					.andExpect(status().isUnauthorized());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(5)
	void testUpdate() {
		try {
			Restaurant newRestaurant = Utils.getAllRestaurants(this.mockMvc).get(0);
			
			String token = Utils.obtainAccessToken(this.mockMvc,"sa", "password");
			
			this.mockMvc.perform(put("/api/v1/restaurant/"+newRestaurant.getRestaurantId()+"/update")
							.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"updated\"}")
							.header("Authorization", "Bearer "+token))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(6)
	void testDeleteNoAuth() {
		try {
			Restaurant newRestaurant = Utils.getAllRestaurants(this.mockMvc).get(0);
			
			this.mockMvc.perform(delete("/api/v1/restaurant/"+newRestaurant.getRestaurantId()+"/delete"))
					.andExpect(status().isUnauthorized());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(7)
	void testDelete() {
		try {
			String token = Utils.obtainAccessToken(this.mockMvc,"sa", "password");
			
			Restaurant newRestaurant = Utils.getAllRestaurants(this.mockMvc).get(0);
			
			this.mockMvc.perform(delete("/api/v1/restaurant/"+newRestaurant.getRestaurantId()+"/delete")
					.header("Authorization", "Bearer "+token))
					.andExpect(status().isNoContent());
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(8)
	void testSaveBlankName() {
		try {
			String token = Utils.obtainAccessToken(this.mockMvc,"sa", "password");
			
			this.mockMvc.perform(post("/api/v1/restaurant/save")
							.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"\"}")
							.header("Authorization", "Bearer "+token))
					.andExpect(status().isBadRequest());
			
		} catch (Exception e) {
			fail(e);
		}
	}
	
}

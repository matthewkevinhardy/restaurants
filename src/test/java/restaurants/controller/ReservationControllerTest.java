package restaurants.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;
import restaurants.test.Utils;
import static restaurants.util.Utils.DATE_FORMAT;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@Autowired
	//private 
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	@Order(0)
	void testGetReservationError() {
		try {
			this.mockMvc.perform(get("/api/v1/reservation/1")).andExpect(status().isNotFound());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	@Order(1)
	void testSave() {
		try {
			Restaurant rest = Utils.createRestaurant(mockMvc, "reservation test restaurant");
			RestaurantTable table = Utils.createTable(mockMvc, 6, rest.getRestaurantId());
			
			LocalDateTime now = LocalDateTime.now();
			Reservation reservation = new Reservation(table.getTableId(),now.plusHours(1),now.plusHours(2));
			
			this.mockMvc.perform(post("/api/v1/reservation/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(reservation))).andExpect(status().isCreated());
			
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(2)
	void testNotFound() {
		try {
			Restaurant restaurant = Utils.getAllRestaurants(mockMvc).get(0);
			
			LocalDate tomorrow = LocalDate.now().plusDays(1);
			
			this.mockMvc.perform(get("/api/v1/restaurant/"+restaurant.getRestaurantId()+"/reservations")
					.param("date",tomorrow.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
			.andExpect(status().isNotFound());
			
		} catch (Exception e) {
			fail(e);
		}
	}
	
	@Test
	@Order(3)
	void testUpdateBlankField() {
		try {
			LocalDateTime now = LocalDateTime.now();
			Reservation reservationWithNoStart = new Reservation(1,null,now.plusHours(2));
			
			this.mockMvc.perform(put("/api/v1/reservation/1/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(reservationWithNoStart)))
			.andExpect(status().isBadRequest());
			
		} catch (Exception e) {
			fail(e);
		}
	}

	

	@Test
	@Order(4)
	void testDelete() {
		try {
			this.mockMvc.perform(delete("/api/v1/reservation/1"))
			.andExpect(status().isNoContent());
			
		} catch (Exception e) {
			fail(e);
		}
	}

}

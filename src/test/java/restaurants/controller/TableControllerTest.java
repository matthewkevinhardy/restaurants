package restaurants.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import restaurants.model.RestaurantTable;

@SpringBootTest
@AutoConfigureMockMvc
class TableControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testUpdateBlankField() {
		try {
			
			String tableJsonNoRestId = "{" + 
					"  \"seatingCapacity\": 1," + 
					"  \"tableId\": 1" + 
					"}";
			
			this.mockMvc.perform(post("/api/v1/table/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content(tableJsonNoRestId))
			.andExpect(status().isBadRequest());
			
		} catch (Exception e) {
			fail(e);
		}
	}

	
}

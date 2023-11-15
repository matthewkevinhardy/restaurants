package restaurants.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import restaurants.security.JwtResponse;
import restaurants.test.Utils;

@SpringBootTest
@AutoConfigureMockMvc
class TableControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testUpdateBlankField() {
		try {

			JwtResponse jwtResponse = Utils.obtainAccessToken(mockMvc, "sa", "password");

			String tableJsonNoRestId = "{" + "  \"seatingCapacity\": 1," + "  \"tableId\": 1" + "}";

			this.mockMvc
					.perform(post("/restaurants/api/v1/table/save").contentType(MediaType.APPLICATION_JSON)
							.content(tableJsonNoRestId).header("Authorization", jwtResponse.getToken()))
					.andExpect(status().isBadRequest());

		} catch (Exception e) {
			fail(e);
		}
	}

}

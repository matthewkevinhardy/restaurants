package restaurants.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import restaurants.model.Restaurant;
import restaurants.model.RestaurantTable;
import restaurants.security.JwtRequest;
import restaurants.security.JwtResponse;

public class Utils {
	
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static List<Restaurant> getAllRestaurants(MockMvc mockMvc) throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/restaurants")).andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		return Arrays.asList(objectMapper.readValue(content, Restaurant[].class));
	}
	
	public static JwtResponse obtainAccessToken(MockMvc mockMvc,String username, String password) throws Exception {
		 
	    //String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
	 
	    JwtRequest jwtRequest = new JwtRequest(username, password);
	    
	    ResultActions result 
	      = mockMvc.perform(post("/api/v1/authenticate")
	        .content(OBJECT_MAPPER.writeValueAsString(jwtRequest))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	 
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JwtResponse jwtResponse = OBJECT_MAPPER.readValue(resultString, JwtResponse.class);
	    return jwtResponse;
	}
	
	public static Restaurant createRestaurant(MockMvc mockMvc,String name) throws Exception {
		JwtResponse jwtResponse = obtainAccessToken(mockMvc,"sa", "password");
		
		MvcResult result = mockMvc.perform(post("/api/v1/restaurant/save")
						.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\""+name+"\"}")
						.header("Authorization", "Bearer "+jwtResponse.getToken()))
				.andExpect(status().isCreated()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		return OBJECT_MAPPER.readValue(content, Restaurant.class);
	}
	
	public static RestaurantTable createTable(MockMvc mockMvc,int seatingCapacity,int restaurantId) throws Exception {
		RestaurantTable table = new RestaurantTable(seatingCapacity, restaurantId);
		
		MvcResult result = mockMvc.perform(post("/api/v1/table/save")
						.contentType(MediaType.APPLICATION_JSON).content(OBJECT_MAPPER.writeValueAsString(table))
						)
				.andExpect(status().isCreated()).andReturn();
		
		String content = result.getResponse().getContentAsString();
		return OBJECT_MAPPER.readValue(content, RestaurantTable.class);
	}
}

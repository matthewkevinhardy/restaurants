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

public class Utils {
	
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static List<Restaurant> getAllRestaurants(MockMvc mockMvc) throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/restaurants")).andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		return Arrays.asList(objectMapper.readValue(content, Restaurant[].class));
	}
	
	public static String obtainAccessToken(MockMvc mockMvc,String username, String password) throws Exception {
		 
	    String json = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
	 
	    ResultActions result 
	      = mockMvc.perform(post("/api/v1/authenticate")
	        .content(json)
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	 
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("token").toString();
	}
	
	public static Restaurant createRestaurant(MockMvc mockMvc,String name) throws Exception {
		String token = obtainAccessToken(mockMvc,"sa", "password");
		
		MvcResult result = mockMvc.perform(post("/api/v1/restaurant/save")
						.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\""+name+"\"}")
						.header("Authorization", "Bearer "+token))
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

package restaurants.security;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -303873200682757762L;
	
	private final String token;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public JwtResponse(@JsonProperty("token") String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
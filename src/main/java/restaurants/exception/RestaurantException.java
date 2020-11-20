package restaurants.exception;

public class RestaurantException extends RuntimeException {

	private static final long serialVersionUID = -4260345324324440099L;
	
	public RestaurantException(String message) {
		super(message);
	}
}

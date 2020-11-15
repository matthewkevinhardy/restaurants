package restaurants.exception;

public class ReservationException extends RuntimeException {

	private static final long serialVersionUID = -4260345324324440099L;
	
	public ReservationException(String message) {
		super(message);
	}
}

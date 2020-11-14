package restaurants.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4260345324324440099L;
	
	public NotFoundException(String message) {
		super(message);
	}
}

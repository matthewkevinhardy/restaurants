package restaurants.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestaurantControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e,HttpServletRequest request) {
		LOG.error("Exception: ",e);
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e,HttpServletRequest request) {
		return error(HttpStatus.NOT_FOUND,e,request);
	}
	
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<ErrorResponse> handleReservationException(ReservationException e,HttpServletRequest request) {
		return error(HttpStatus.BAD_REQUEST,e,request);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp",LocalDateTime.now(ZoneOffset.UTC));
        body.put("status", HttpStatus.BAD_REQUEST);
        
		//Get all errors
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
		body.put("errors", errors);
		body.put("path",request.getRequestURI());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	
	private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e,HttpServletRequest request) {
		return ResponseEntity.status(status).body(
				new ErrorResponse(status, status.value()+"", e.getMessage(), request.getRequestURI(), LocalDateTime.now(ZoneOffset.UTC)));
	}
}

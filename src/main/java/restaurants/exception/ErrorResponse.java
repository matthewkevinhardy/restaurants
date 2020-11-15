package restaurants.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus status;
    private String error_code;
    private String message;
    private String path;
    private LocalDateTime timeStamp;
	public ErrorResponse(HttpStatus status, String error_code, String message, String path, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.error_code = error_code;
		this.message = message;
		this.path = path;
		this.timeStamp = timeStamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
    
    
}

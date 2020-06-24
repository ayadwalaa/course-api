package springbootquickstarter.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class APIErrors extends Exception {
	private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String debugMessage;
	 

	  public APIErrors() {
	      // timestamp = LocalDateTime.now();
		  super();
	   }

	   APIErrors(HttpStatus status) {
	       this();
	       this.status = status;
	   }

	   APIErrors(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	   APIErrors(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	   public APIErrors(final String message){
		   super(message);
	   }
	
}

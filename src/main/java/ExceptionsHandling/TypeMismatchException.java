package ExceptionsHandling;

import java.time.LocalDateTime;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.annotation.JsonFormat;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class TypeMismatchException extends Exception{

	
	public TypeMismatchException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
	 
	public String getTimestamp() {
		return timestamp.toString();
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}

package springbootquickstarter.exceptionshandling;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class TypeMismatchException extends Exception {

	public TypeMismatchException(String string) {

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

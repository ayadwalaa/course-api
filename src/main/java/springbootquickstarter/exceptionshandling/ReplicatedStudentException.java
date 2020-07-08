package springbootquickstarter.exceptionshandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class ReplicatedStudentException extends Exception {

	public ReplicatedStudentException(String message) {
		super(message);
	}

}

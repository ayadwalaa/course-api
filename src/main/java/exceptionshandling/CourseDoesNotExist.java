package exceptionshandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseDoesNotExist extends Exception {

	public CourseDoesNotExist(String message) {
		super(message);

	}

}

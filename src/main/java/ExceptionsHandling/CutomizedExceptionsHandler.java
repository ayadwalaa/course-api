package ExceptionsHandling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import ExceptionsHandling.StudentNotFoundException;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CutomizedExceptionsHandler {
	 @ExceptionHandler(Exception.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Server Error", details);
	        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	    @ExceptionHandler(StudentNotFoundException.class)
	    public final ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        details.add(ex.getMessage());
	        ErrorResponse error = new ErrorResponse("Student Not Found", details);
	        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	    }
	    @ExceptionHandler(CourseDoesNotExist.class)
	    public final ResponseEntity<Object> handleCourseNotFoundException(CourseDoesNotExist ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        details.add(ex.getMessage());
	        ErrorResponse error = new ErrorResponse("Course Not Found", details);
	        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	    }
	 
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
	            details.add(error.getDefaultMessage());
	        }
	        ErrorResponse error = new ErrorResponse("Validation Failed", details);
	        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(TypeMismatchException.class)
	    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, WebRequest request) {
	    	 List<String> details = new ArrayList<>();
		        details.add(ex.getLocalizedMessage());
		        details.add(ex.getTimestamp());
		        ErrorResponse error = new ErrorResponse(" Type mismatch in the request.", details);
		        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(ReplicatedStudentException.class)
	    protected ResponseEntity<Object> handleReplicatedStudentException(ReplicatedStudentException ex, WebRequest request) {
	    	 List<String> details = new ArrayList<>();
		        details.add(ex.getLocalizedMessage());
		        ErrorResponse error = new ErrorResponse(" Replicated record.", details);
		        return new ResponseEntity<Object>(error, HttpStatus.ALREADY_REPORTED);
	    }
	    
	
	  @ExceptionHandler(DataIntegrityViolationException.class) 
	  protected ResponseEntity<Object> ReplicatedStudent(DataIntegrityViolationException ex,WebRequest request) {
	  List<String> details = new ArrayList<>();
	  details.add(ex.getLocalizedMessage());
	  ErrorResponse error = new ErrorResponse(" Replicated record.", details);
	  return new ResponseEntity<Object>(error, HttpStatus.ALREADY_REPORTED); }
	 
	    
	    
}

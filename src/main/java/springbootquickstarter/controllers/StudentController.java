package springbootquickstarter.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import DTOs.*;
import ExceptionsHandling.StudentNotFoundException;
import ExceptionsHandling.TypeMismatchException;
import springbootquickstarter.entities.*;
import springbootquickstarter.services.*;
import springbootquickstarter.repositories.StudentRepository;

@RestController
@Validated

public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired 
	StudentConverter converter;
	
	@GetMapping("/students")
	public List <StudentDTO> getAllStudents(){
		return studentService.getStudents();
	}

	  @GetMapping("/students/{id}") 
   public ResponseEntity<StudentDTO> getStudent( @PathVariable(value = "id")  @Valid @Min(10) @Max(1000) Long id) throws StudentNotFoundException, ConstraintViolationException, TypeMismatchException { 
		  return studentService.getStudent(id);
	  }
	  
	  @PostMapping("/students")
   public ResponseEntity<StudentDTO> addStudent(@RequestBody Student studentt) throws Exception {
		return studentService.addStudent(studentt);
	  }
   @PutMapping("/students/{studentid}")
   public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Long studentid) throws StudentNotFoundException{ 
		return studentService.editStudent(dto, studentid);
		    } 

	@DeleteMapping("/students/{studentid}") 
	  public ResponseEntity<Void> deleteStudent(@PathVariable Long studentid) throws StudentNotFoundException {
		  return studentService.deleteStudent(studentid);
		 
	  }
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	/*********************************
	 * Without DTOs
	 ***************************************************/
	
	/*
	 * @RequestMapping (value = "/students") public Set <Student> getAllStudents(){
	 * return studentService.getStudents(); }
	 */
	  
	/*
	 * @PostMapping(value = "/students") public void addStudent (@RequestBody
	 * Student student) { if (studentRepo.exists(student.getIdNumber())) { }
	 * studentService.addStudent(student); }
	 */
	  
	/*
	 * @GetMapping(value="/students/{studentid}") public Student
	 * getStudent(@PathVariable Long studentid) { if
	 * (!studentRepo.exists(studentid)) { throw new
	 * RuntimeException("Student does not exist. Please recheck the ID provided.");
	 * } else { return studentService.getStudent(studentid); } }
	 */
	  
	/*
	 * @PutMapping(value ="/students/{studentid}") public void
	 * updateStudent(@RequestBody Student student, @PathVariable Long studentid) {
	 * if (!studentRepo.exists(studentid)) { throw new
	 * RuntimeException("Student does not exist. Please recheck the ID provided.");
	 * } else { studentService.editStudent(student, studentid); } }
	 */
	  
	/*
	 * @DeleteMapping(value ="students/{studentid}") public void
	 * deleteStudent(@PathVariable Long studentid) { if
	 * (!studentRepo.exists(studentid)) { throw new
	 * RuntimeException("Student does not exist. Please recheck the ID provided.");
	 * } else { studentService.deleteStudent(studentid); } }
	 */
	 

}

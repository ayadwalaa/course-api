package springbootquickstarter.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import DTOs.*;
import springbootquickstarter.entities.*;
import springbootquickstarter.services.*;
import springbootquickstarter.repositories.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired 
	StudentConverter converter;
	
	@GetMapping("/students")
	public List <StudentDTO> getAllStudents(){
		List <Student> allStudents = studentRepo.findAll();
		if (allStudents.size()==0) {
			return null;
		}
		return converter.entityToDto(allStudents);
		
	}
	
	
	  @GetMapping("/students/{id}") 
	  public ResponseEntity<StudentDTO> getStudent(@PathVariable(value = "id") Long id) { 
		  if (studentRepo.exists(id)) {
			  Student student = studentRepo.findOne(id);
			  return ResponseEntity.ok(converter.entityToDto(student));
		  }
		 return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	  
	  }
	  
	
	  @PostMapping("/students") 
	  public ResponseEntity<StudentDTO> addStudent(@RequestBody Student studentt) {
		  if (studentRepo.exists(studentt.getId())) {
			  return new ResponseEntity<> (HttpStatus.ALREADY_REPORTED);
		  }
		  else {
			  
		if (studentt.getIdNumber().SIZE > 12 || studentt.getIdNumber().SIZE < 10) {
			return new ResponseEntity<>(HttpStatus.LENGTH_REQUIRED);
		}
		else {
		 studentRepo.save(studentt); 
		 return ResponseEntity.ok(converter.entityToDto(studentt));
		}
		
		  }
	  }
	  
	@PutMapping ("/students/{studentid}") 
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Long studentid) { 
		
		if (studentRepo.exists(studentid)) {
			Student student = converter.dtoToEntity(dto);
		    studentRepo.save(student);
		    ResponseEntity.ok(converter.entityToDto(student));
		}
		
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 
		    } 
		 
	 
	  
	
	  @DeleteMapping("/students/{studentid}") 
	  public ResponseEntity<Void> deleteStudent(@PathVariable Long studentid) {
		  if (studentRepo.exists(studentid)) {
			  studentService.deleteStudent(studentid);
			  return ResponseEntity.noContent().build();
		  }
			 return ResponseEntity.notFound().build();
		 
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

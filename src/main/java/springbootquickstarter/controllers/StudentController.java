package springbootquickstarter.controllers;

import java.util.ArrayList;
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
		  List <Student> students = studentRepo.findAll();
		 List <Long> ids= new ArrayList<>();
		 for (int i =0; i< students.size();i++) {
			 ids.add(i, students.get(i).getId());
		 }
		 for (int j=0; j<ids.size(); j++) {
			 if (id == ids.get(j)) {
				 Student student =studentRepo.findOne(id);
				   return ResponseEntity.ok(converter.entityToDto(student));
			 }
		 }
		 return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	  
	  }
	  
	
	  @PostMapping("/students") 
	  public ResponseEntity<StudentDTO> addStudent(@RequestBody Student studentt) {
		  List <Student> students = studentRepo.findAll();
			 List <Long> ids= new ArrayList<>();
			 for (int i =0; i< students.size();i++) {
				 ids.add(i, students.get(i).getIdNumber());
			 }
		  for (int i=0; i<ids.size();i++) {
			  if (studentt.getIdNumber().equals(ids.get(i))) {
				  return new ResponseEntity<> (HttpStatus.ALREADY_REPORTED);
			  }
			  
		  }
		 studentRepo.save(studentt); 
		 return ResponseEntity.ok(converter.entityToDto(studentt));
		
		  }
	 
	  
	@PutMapping ("/students/{studentid}") 
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Long studentid) { 
		 List <Student> students = studentRepo.findAll();
		 List <Long> ids= new ArrayList<>();
		 for (int i =0; i< students.size();i++) {
			 ids.add(i, students.get(i).getId());
		 }
		 for ( int i =0; i<ids.size();i++) {
			 if (ids.get(i).equals(studentid)) {
				 Student student = converter.dtoToEntity(dto); 
					studentService.editStudent(student, studentid);
					student=studentRepo.save(student); 
					return ResponseEntity.ok(converter.entityToDto(student));
			 }
		 }
		
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 
		    } 
		 
	 
	  
	
	  @DeleteMapping("/students/{studentid}") 
	  public ResponseEntity<Void> deleteStudent(@PathVariable Long studentid) {
		  List <Student> students = studentRepo.findAll();
			 List <Long> ids= new ArrayList<>();
			 for (int i =0; i< students.size();i++) {
				 ids.add(i, students.get(i).getId());
			 }
			 for (int i =0; i<ids.size(); i++) {
				 if (ids.get(i).equals(studentid)) {
					 studentService.deleteStudent(studentid);
					  return ResponseEntity.noContent().build();
				 }
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
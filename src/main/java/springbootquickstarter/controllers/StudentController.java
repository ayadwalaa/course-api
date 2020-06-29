package springbootquickstarter.controllers;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootquickstarter.entities.*;
import springbootquickstarter.services.*;
import springbootquickstarter.repositories.CourseRepository;
import springbootquickstarter.repositories.StudentRepository;
import springbootquickstarter.repositories.TopicRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	TopicRepository topicRepo;
	
	  @RequestMapping (value = "/students")
	  public Set <Student> getAllStudents(){ 
		  if(studentRepo.findAll() == null) {
			  throw new RuntimeException("There are not any students.");
		  }
		  return studentService.getStudents(); 
		  }
	
	  @PostMapping(value = "/students")
	  public void addStudent (@RequestBody Student student) { 
		  
		  studentService.addStudent(student); 
		  }
	  
	  @GetMapping(value="/students/{studentid}") 
	  public Student getStudent(@PathVariable Long studentid) { 
		  if (!studentRepo.exists(studentid)) {
			  throw new RuntimeException("Student does not exist. Please recheck the ID provided.");  
		  }
		  else 
		  {
		  return studentService.getStudent(studentid); 
		  }
		  }
	  
	   @PutMapping(value ="/students/{studentid}") 
	  public void updateStudent(@RequestBody Student student, @PathVariable Long studentid) 
	  { 
		   if (!studentRepo.exists(studentid)) {
				  throw new RuntimeException("Student does not exist. Please recheck the ID provided.");  
			  }
			  else
			  {
		   studentService.editStudent(student, studentid);
		   }
	  }

	  @DeleteMapping(value ="students/{studentid}") 
	  public void deleteStudent(@PathVariable Long studentid) 
	  {
		  if (!studentRepo.exists(studentid)) {
			  throw new RuntimeException("Student does not exist. Please recheck the ID provided.");  
		  }
		  else {
	  studentService.deleteStudent(studentid); 
		  }
	  }

	
}

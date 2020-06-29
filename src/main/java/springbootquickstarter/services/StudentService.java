package springbootquickstarter.services;


import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootquickstarter.entities.Student;
import springbootquickstarter.repositories.CourseRepository;
import springbootquickstarter.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	CourseRepository courseRepo;
	
	  public Set<Student> getStudents() 
	  { 
				Set <Student> students= new HashSet<>();
				studentRepo.findAll()
				.forEach(students::add);
				return students;
			}

	  public void addStudent(Student student)
	  {
	  studentRepo.save(student);
	  }
	
	  public Student getStudent(Long studentid) 
	  { 
		  return studentRepo.findOne(studentid); 
	 }
	  
	  public void editStudent(Student student, Long studentId) 
	  {
		  studentRepo.save(student); 
		  }
	  
	  public void deleteStudent(Long studentid)
	  { 
		  studentRepo.delete(studentid); 
		  }
	 
	 
	 
}

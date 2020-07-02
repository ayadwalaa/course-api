package springbootquickstarter.services;
import ExceptionsHandling.*;


import java.util.List;
import javax.persistence.*;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import DTOs.StudentDTO;
import springbootquickstarter.entities.Student;
import springbootquickstarter.repositories.CourseRepository;
import springbootquickstarter.repositories.StudentRepository;
import springbootquickstarter.controllers.*;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentConverter converter;
	

	public List<StudentDTO> getStudents() { 
		List<Student> allStudents = studentRepo.findAll();
		if (allStudents.size()==0) {
			throw new EntityNotFoundException();
		}
		return converter.entityToDto(allStudents);
	}
	
	
	public ResponseEntity<StudentDTO> addStudent(Student student) throws Exception {
		if(!studentRepo.exists(student.getIdNumber())) {
			return ResponseEntity.ok(converter.entityToDto(studentRepo.save(student)));
		}
		else {
		throw new DataIntegrityViolationException("Student already exists.");
		}
	}
	

	public ResponseEntity<StudentDTO> getStudent(Long studentid) throws StudentNotFoundException, TypeMismatchException, ConstraintViolationException { 
		return ResponseEntity.ok(converter.entityToDto(findStudent(studentid)));	 
	}

	public ResponseEntity<StudentDTO> editStudent(StudentDTO studentDto, Long studentId) throws StudentNotFoundException{
		if (studentRepo.exists(studentId)) {
		return ResponseEntity.ok(converter.entityToDto(studentRepo.save(converter.dtoToEntity(studentDto))));
		}
		else {
			throw new StudentNotFoundException("Student with id = " + studentId + " does not exist.");
		}
	}

	
	public ResponseEntity<Void> deleteStudent(Long studentid) throws StudentNotFoundException{ 
		studentRepo.delete(findStudent(studentid).getId());
		return ResponseEntity.ok().build();
	}
	
	public Student findStudent(Long studentid) throws StudentNotFoundException  {
		Student student = studentRepo.findOne(studentid);
		if(student != null) {
			return student;
		}
		throw new StudentNotFoundException("Student with id = " +studentid + " can not be found.");
		 
	}
	public Student invalidArgument (Long studentid) throws ConstraintViolationException, TypeMismatchException{
		if (studentid.TYPE != long.class) {
			throw new TypeMismatchException("Argument type is invalid.");
		}
		else {
			return studentRepo.findOne(studentid);
		}
	}
	
	


}

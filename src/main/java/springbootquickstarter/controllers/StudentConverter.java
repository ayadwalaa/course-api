package springbootquickstarter.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import DTOs.StudentDTO;
import springbootquickstarter.entities.Student;

@Component
public class StudentConverter {
public StudentDTO entityToDto(Student student) {
		
		StudentDTO dto = new StudentDTO();
		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setCourses(student.getCourses());
		return dto;
	}

	public List<StudentDTO> entityToDto(List<Student> student) {
		return	student.stream().map(x -> entityToDto(x)).collect(Collectors.toList());	
	}
	
	
	public Student dtoToEntity(StudentDTO dto)
	{
		Student student = new Student();
		student.setId(dto.getId());
		student.setName(dto.getName());
		student.setCourses(dto.getCourses());
		return student;
	}
	
	public List<Student> dtoToEntity(List<StudentDTO> dto)
	{

		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}

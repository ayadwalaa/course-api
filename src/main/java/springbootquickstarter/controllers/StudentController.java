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

import exceptionshandling.StudentNotFoundException;
import exceptionshandling.TypeMismatchException;
import springbootquickstarter.dtos.StudentConverter;
import springbootquickstarter.dtos.StudentDTO;
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
	public List<StudentDTO> getAllStudents() {
		return studentService.getStudents();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable(value = "id") @Valid @Min(10) @Max(1000) Long id)
			throws StudentNotFoundException, ConstraintViolationException, TypeMismatchException {
		return studentService.getStudent(id);
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDTO> addStudent(@RequestBody Student studentt) throws Exception {
		return studentService.addStudent(studentt);
	}

	@PutMapping("/students/{studentid}")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO dto, @PathVariable Long studentid)
			throws StudentNotFoundException {
		return studentService.editStudent(dto, studentid);
	}

	@DeleteMapping("/students/{studentid}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentid) throws StudentNotFoundException {
		return studentService.deleteStudent(studentid);

	}

}

package DTOs;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import springbootquickstarter.entities.*;

@Entity
public class StudentDTO  {
	private String name;
	private Long idNumber;
	private Set <Course> courses = new HashSet<>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return idNumber;
	}
	public void setId(Long id) {
		this.idNumber = id;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	

}

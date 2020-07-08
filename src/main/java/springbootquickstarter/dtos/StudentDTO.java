package springbootquickstarter.dtos;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import springbootquickstarter.entities.*;

@Entity
public class StudentDTO {
	@NotNull
	private String name;
	@NotNull
	@Size(min = 10, max = 12)
	@Id
	private Long idNumber;

	@ManyToMany
	private Set<Course> courses = new HashSet<>();

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

package springbootquickstarter.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


@Entity
@Table (name= "student_tbl")
public class Student {
	
	@Column(name="idNumber", nullable=false, unique=true, length=12)
	@NotNull(message = "ID Number Should not be Null.")
	//@Digits(integer=12, fraction=0, message="Invalid ID Number")
	private Long idNumber;
	
	@Column (name= "studentName", nullable= false)
	private String name; 
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="studentId")
	private Long id;
	
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity= Course.class)
	@JoinTable(name = "students_courses",
    joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studentId",
                                nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "courseId",
                               nullable = false, updatable = false)})
	@Column (name = "studentCourses")
	private Set <Course> courses = new HashSet<>();

	public Student() {
		super();
	}

	public Student(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Student(Long id, String name, Set <Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	public Set <Course> getCourses() {
		return courses;
	}


	@SuppressWarnings("unchecked")
	public void setCourses(Long courseId, String string1, String string2, Long topicId) {
		Set <Course> course = (Set <Course>) new Course(courseId, string1,string2, topicId);
		this.courses = course;
	}

	public void setCourses(Set<Course> courses2) {
		this.courses= courses2;
	}
	

}

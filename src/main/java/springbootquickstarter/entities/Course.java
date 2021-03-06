package springbootquickstarter.entities;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "courses_tbl")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="courseId")
	private Long id; 
	@Column (name= "courseName")
	private String name;
	@Column (name="courseDescription")
	private String description;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne (fetch = FetchType.LAZY, optional = false)
	@JoinColumn (name = "topicId", nullable=false)
	private Topic topic;
	

	@JsonBackReference
	@ManyToMany (mappedBy="courses", targetEntity= Student.class, cascade = CascadeType.PERSIST)	
	@Column (name = "studentsCourses")
	private List  <Student> students ;
	
	
	
	public Course() {}
	
	public Course(Long id, String name, String de, Long topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = de;
		this.topic= new Topic(topicId, "","");
	}
	
	public Course(Long id, String name){
		super();
		this.id = id;
	      this.name = name;
	    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Long topicId, String string, String string2) {
	this.topic = new Topic(topicId, string, string2);
	}

	public List <Student> getStudents() {
		return students;
	}

	public void setStudents(Student student) {
		this.students.add(student);
	}

	
}
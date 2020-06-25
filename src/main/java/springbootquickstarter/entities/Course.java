package springbootquickstarter.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Long topicId, String string, String string2) {
	this.topic = new Topic(topicId, string, string2);
	}

	public Course() {}
	
	public Course(Long id, String name, String de, Long topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = de;
		this.topic= new Topic(topicId, "","");
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
}
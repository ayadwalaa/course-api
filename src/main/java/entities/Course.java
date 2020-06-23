package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Course {

	
	// In order to use the course's ID as the primary key in the relational database, we use the primary key as a notation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id; 
	private String name;
	private String description;
	
	@ManyToOne
	private Topic topics;
	

	public Topic getTopic() {
		return topics;
	}

	public void setTopic(String id, String string, String string2) {
		this.topics = new Topic(id, string, string2);
	}

	public Course() {}
	
	public Course(String id, String name, String de, String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = de;
		this.topics= new Topic(topicId, "","");
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

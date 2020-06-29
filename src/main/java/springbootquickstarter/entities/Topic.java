package springbootquickstarter.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// configuring the relations
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name ="topics_tbl")
public class Topic{
	//primary key
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="topicId")
	private Long id;
	@Column (name ="topicName")
	private String name;
	@Column (name= "topicDescription")
	private String description;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany (mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Course> courses;
	
	public Topic() {}
	
	public Topic(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

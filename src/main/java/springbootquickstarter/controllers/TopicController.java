package springbootquickstarter.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springbootquickstarter.entities.Topic;
import springbootquickstarter.repositories.TopicRepository;
import springbootquickstarter.services.TopicService;

@RestController
public class TopicController {
	// To declare the dependency
	@Autowired
	private TopicService topicservice; 
	@Autowired
	private TopicRepository topicreop;
	
	@RequestMapping("/topics")
	public List <Topic> AllTopics() {
		
		  if(topicservice.getAllTopics().isEmpty()) {
			  throw new RuntimeException("There are no topics. "); 
			  }
		return topicservice.getAllTopics() ;	
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable Long id)  {
		
		 if(!topicreop.exists(id)) { 
			 throw new RuntimeException("Topic does not exist. Please recheck the ID provided."); 
			 }
		 
	return topicservice.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic)  {
			topicservice.addTopic(topic);
		}
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable Long id) {
		
		  if(!topicreop.exists(id)) { 
			  throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
			  }
			topicservice.updateTopic(id,topic);	
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public void deleteTopic(@PathVariable Long id) {
		  if(!topicreop.exists(id)) { 
			  throw new RuntimeException("Topic does not exist. Please recheck the ID provided."); 
			  }
		topicservice.deleteTopic(id);	
	}
}

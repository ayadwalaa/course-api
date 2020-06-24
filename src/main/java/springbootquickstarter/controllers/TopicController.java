package springbootquickstarter.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springbootquickstarter.entities.Topic;
import springbootquickstarter.services.TopicService;

@RestController
public class TopicController {
	// To declare the dependency
	@Autowired
	private TopicService topicservice; 
	
	@RequestMapping("/topics")
	public List <Topic> AllTopics() {
		return topicservice.getAllTopics() ;
	}
	
	// This can be enhanced to return a proper error message if a certain topic isn't available
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable Long id)  {
	return topicservice.getTopic(id);
	}
	
	
	// If the object has been mistakenly created, we can show a proper error message
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic)  {
			topicservice.addTopic(topic);
		}
	// If topic doesn't exist or mistakenly updated
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable Long id) {
			topicservice.updateTopic(id,topic);
	}
	
	// If topic doesn't exist
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@RequestBody Topic topic, @PathVariable Long id) {
		topicservice.deleteTopic(id,topic);
		
		
	}
}

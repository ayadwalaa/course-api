package Topics;

// This class is to know what would the URL include, and what will happen when an exact URL is requested
// Methods implemented in this class, are all about request mapping

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
//in spring business services are similar to singleton 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class TopicController {
	// To declare the dependency
	@Autowired
	private TopicService topicservice; 
	private ResponseEntity responseentity;
	
	
	@RequestMapping("/topics")
	public List <Topic> AllTopics() {
		return topicservice.getAllTopics() ;
	}
	
	// This can be enhanced to return a proper error message if a certain topic isn't available
	@SuppressWarnings("finally")
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) throws APIErrors {
		try {
	return topicservice.getTopic(id);
	}
		catch(Exception exception) {
			throw new APIErrors("Topic could not be found.");
		}	
	}
	
	
	// If the object has been mistakenly created, we can show a proper error message
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) throws Exception {
		try{
			topicservice.addTopic(topic);
		}
		catch (Exception exception) {
			throw new APIErrors ("An issue in adding a new topic");
		}
		}
		
	
	
	// If topic doesn't exist
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) throws APIErrors {
		try{
			topicservice.updateTopic(id,topic);
		}
		catch (Exception exception) {
			throw new APIErrors("Course could not be updated.");
		}
		
	}
	
	// If topic doesn't exist
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@RequestBody Topic topic, @PathVariable String id) throws APIErrors {
		try{
			topicservice.deleteTopic(id,topic);
		}
		catch(Exception exception) {
			throw new APIErrors("Topic could not be deleted.");
		}
		
	}
}

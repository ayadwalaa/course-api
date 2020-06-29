package springbootquickstarter.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootquickstarter.entities.Topic;
import springbootquickstarter.repositories.TopicRepository;


@Service
public class TopicService {
	@Autowired
	private TopicRepository topicRepository;
	

public List <Topic> getAllTopics(){
	//return topics;
	List <Topic> topics= new ArrayList<>();
	topicRepository.findAll()
	.forEach(topics::add);
	return topics;
}
public Topic getTopic(Long id) {
	 return topicRepository.findOne(id);
}
public void addTopic(Topic topic) {
	topicRepository.save(topic);	
}
public void updateTopic(Long id, Topic topic) {
	topicRepository.save(topic);
}

public void deleteTopic(Long id) {
	topicRepository.delete(id);
	
}
}

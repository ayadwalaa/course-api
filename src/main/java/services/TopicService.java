package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Topic;
import repositories.TopicRepository;


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
public Topic getTopic(String id) {
	 return topicRepository.findOne(id);
}
public void addTopic(Topic topic) {
	topicRepository.save(topic);	
}
public void updateTopic(String id, Topic topic) {
	topicRepository.save(topic);
}
public void deleteTopic(String id, Topic topic) {
	topicRepository.delete(id);
	
}
}

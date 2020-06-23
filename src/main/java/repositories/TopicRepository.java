package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Topic;

public interface TopicRepository extends CrudRepository <Topic, String>{
	

}

package repositories;

import org.springframework.data.repository.CrudRepository;

import entities.Course;

import java.util.List;


public interface CourseRepository extends CrudRepository <Course, String>{
	public List <Course> findByTopicId(String topicId);
	

}


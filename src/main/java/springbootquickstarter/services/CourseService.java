package springbootquickstarter.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootquickstarter.entities.Course;
import springbootquickstarter.repositories.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository topicRepository;
	

public List <Course> getAllCourses(Long topicId){
	//return topics;
	List <Course> topics= new ArrayList<>();
	topicRepository.findByTopicId(topicId)
	.forEach(topics::add);
	return topics;
}
public Course getCourse(Long id) {
	 return topicRepository.findOne(id);
}
public void addCourse(Course topic) {
	topicRepository.save(topic);	
}
public void updateCourse(Course topic) {
	topicRepository.save(topic);
}
public void deleteCourse(Long id, Course topic) {
	topicRepository.delete(id);
	
}
}

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
	private CourseRepository courseRepository;
	

public List <Course> getAllCourses(Long topicId){
	//return topics;
	List <Course> topics= new ArrayList<>();
	courseRepository.findByTopicId(topicId)
	.forEach(topics::add);
	return topics;
}
public Course getCourse(Long id) {
	 return courseRepository.findOne(id);
}
public void addCourse(Course topic) {
	courseRepository.save(topic);	
}
public void updateCourse(Course topic) {
	courseRepository.save(topic);
}
public void deleteCourse(Long id) {
	courseRepository.delete(id);
	
}
}
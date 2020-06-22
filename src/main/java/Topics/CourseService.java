package Topics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseService {
	@Autowired
	private CourseRepository topicRepository;
	

public List <Course> getAllCourses(String topicId){
	//return topics;
	List <Course> topics= new ArrayList<>();
	topicRepository.findByTopicId(topicId)
	.forEach(topics::add);
	return topics;
}
public Course getCourse(String id) {
	 return topicRepository.findOne(id);
}
public void addCourse(Course topic) {
	topicRepository.save(topic);	
}
public void updateCourse(Course topic) {
	topicRepository.save(topic);
}
public void deleteCourse(String id, Course topic) {
	topicRepository.delete(id);
	
}
}

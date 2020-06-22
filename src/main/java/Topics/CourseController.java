package Topics;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
//in spring business services are similar to singleton 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CourseController {
	@Autowired
	private CourseService courseService; // to inject this, declare dependencies using autowired
	
	@RequestMapping("/topics/{id}/courses")
	public List <Course> getAllCoursesModified(@PathVariable String id) {
        return courseService.getAllCourses(id) ;
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Course getCourse(@PathVariable String id) {// annotation is to map the variable in the annotation with the argument here
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(topicId,"","");
		courseService.addCourse(course);
	}
	@PutMapping("/courses/{topicId}")
	public void addCourseModified(@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(topicId,"Course number " + topicId,"");
		courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Course course, @PathVariable String id, @PathVariable String topicId) {
		
		course.setTopic(topicId, "Test number" + topicId, "");
		courseService.updateCourse(course);
		
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public void deleteCourse(@RequestBody Course topic, @PathVariable String id) {
		courseService.deleteCourse(id,topic);
		
	}
}

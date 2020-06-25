package springbootquickstarter.controllers;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootquickstarter.entities.Course;
import springbootquickstarter.repositories.CourseRepository;
import springbootquickstarter.repositories.TopicRepository;
import springbootquickstarter.services.CourseService;

//in spring business services are similar to singleton 
@RestController
public class CourseController {
	/*
	 * @Autowired private CourseService courseService; // to inject this, declare
	 * dependencies using auto wired
	 */

	@Autowired
	CourseService courseService;
	@Autowired
	CourseRepository courserepo;
	@Autowired
	TopicRepository topicrepo;
	
	@RequestMapping("/topics/{id}/courses")
	public List <Course> getAllCoursesModified(@PathVariable Long id) {
		
		  if(!topicrepo.exists(id)) {
		   throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
		   } 
        return courseService.getAllCourses(id) ;
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Course getCourse(@PathVariable Long id) {
		 if(!courserepo.exists(id)) {
			   throw new RuntimeException("Course does not exist. Please recheck the ID provided.");
			   } 
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable Long topicId) {
		 if(!topicrepo.exists(topicId)) {
			   throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
			   } 
		course.setTopic(topicId,"","");
		courseService.addCourse(course);
	}
	@PutMapping("/courses/{topicId}")
	public void addCourseModified(@RequestBody Course course, @PathVariable Long topicId) {
		 if(!topicrepo.exists(topicId)) {
			   throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
			   } 
		course.setTopic(topicId,"Course number " + topicId,"");
		courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Course course, @PathVariable Long id, @PathVariable Long topicId) {
		
		course.setTopic(topicId, "Test number" + topicId, "");
		courseService.updateCourse(course);
		
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable Long id) {
		 if(!courserepo.exists(id)) {
			   throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
			   } 
		courseService.deleteCourse(id);	
	}
}
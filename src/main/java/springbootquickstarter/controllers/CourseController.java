package springbootquickstarter.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import exceptionshandling.CourseDoesNotExist;
import springbootquickstarter.entities.Course;
import springbootquickstarter.entities.Student;
import springbootquickstarter.repositories.CourseRepository;
import springbootquickstarter.repositories.TopicRepository;
import springbootquickstarter.services.CourseService;

//in spring business services are similar to singleton 
@RestController
public class CourseController {
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
	public ResponseEntity<Course> getCourse(@PathVariable Long id) throws CourseDoesNotExist {
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
	public ResponseEntity<Course> addCourseModified(@RequestBody Course course, @PathVariable Long topicId) {
		course.setTopic(topicId,"Course number " + topicId,"");
		return courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id, @PathVariable Long topicId) throws CourseDoesNotExist {
		course.setTopic(topicId, "Test number" + topicId, "");
		return courseService.updateCourse(course);	
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) throws CourseDoesNotExist {
		return courseService.deleteCourse(id);	
	}

	
	@RequestMapping("/courses/{id}/students")
	public List <Student> getCourseStudents(@PathVariable Long id) throws CourseDoesNotExist {
		return courseService.getCourseStudents(id);
	}
	
	@PostMapping ("courses/{courseid}/students")
	public ResponseEntity<Course> addStudentsToCourse(@PathVariable Long courseid, @RequestBody Student students) throws CourseDoesNotExist {
		 return courseService.addStudentToCourse(courseid, students);
	}
}
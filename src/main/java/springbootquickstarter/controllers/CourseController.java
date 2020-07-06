package springbootquickstarter.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exceptionshandling.CourseDoesNotExist;
import springbootquickstarter.entities.*;
import springbootquickstarter.repositories.*;
import springbootquickstarter.services.*;

@RestController
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	CourseRepository courserepo;
	@Autowired
	TopicRepository topicrepo;
	@Autowired
	StudentRepository studentRepo;

	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllCoursesModified(@PathVariable Long id) {

		if (!topicrepo.exists(id)) {
			throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
		}
		return courseService.getAllCourses(id);
	}

	@RequestMapping("/topics/{topicId}/courses/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable Long id) throws CourseDoesNotExist {
		return courseService.getCourse(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable Long topicId) {
		if (!topicrepo.exists(topicId)) {
			throw new RuntimeException("Topic does not exist. Please recheck the ID provided.");
		}
		course.setTopic(topicId, "", "");
		courseService.addCourse(course);
	}

	@PutMapping("/courses/{topicId}")
	public ResponseEntity<Course> addCourseModified(@RequestBody Course course, @PathVariable Long topicId) {
		course.setTopic(topicId, "Course number " + topicId, "");
		return courseService.addCourse(course);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id,
			@PathVariable Long topicId) throws CourseDoesNotExist {
		course.setTopic(topicId, "Test number" + topicId, "");
		return courseService.updateCourse(course);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) throws CourseDoesNotExist {
		return courseService.deleteCourse(id);
	}

	@RequestMapping("/courses/students")
	public List<Student> getCoursesStudents() throws CourseDoesNotExist {
		List<Student> students = studentRepo.findAll();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getCourses().size() == 0)
				students.remove(students.get(i));
		}
		return students;
	}

	@RequestMapping("/coursesStudents")
	public List<Student> getStuentsOfCourses(@RequestParam Map<String, String> my_map)
			throws NumberFormatException, CourseDoesNotExist {
		return courseService.getCoursesStudents(my_map);
	}

	@PostMapping("courses/{courseid}/students")
	public ResponseEntity<Course> addStudentsToCourse(@PathVariable Long courseid, @RequestBody Student students)
			throws CourseDoesNotExist {
		return courseService.addStudentToCourse(courseid, students);
	}
}
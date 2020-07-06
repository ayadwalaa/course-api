package springbootquickstarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import exceptionshandling.CourseDoesNotExist;
import springbootquickstarter.entities.Course;
import springbootquickstarter.entities.Student;
import springbootquickstarter.repositories.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses(Long topicId) {
		List<Course> topics = new ArrayList<>();
		courseRepository.findByTopicId(topicId).forEach(topics::add);
		return topics;
	}

	public ResponseEntity<Course> getCourse(Long id) throws CourseDoesNotExist {
		return ResponseEntity.ok(findCourse(id));
	}

	public ResponseEntity<Course> addCourse(Course course) {
		return ResponseEntity.ok(courseRepository.save(course));
	}

	public ResponseEntity<Course> updateCourse(Course course) throws CourseDoesNotExist {
		return ResponseEntity.ok(courseRepository.save(findCourse(course.getId())));
	}

	public ResponseEntity<Void> deleteCourse(Long id) throws CourseDoesNotExist {
		courseRepository.delete(findCourse(id).getId());
		return ResponseEntity.ok().build();
	}

	public List<Student> getCourseStudents(Long id) throws CourseDoesNotExist {
		return findCourse(id).getStudents();
	}

	public ResponseEntity<Course> addStudentToCourse(Long courseid, Student students) throws CourseDoesNotExist {
		Course c = findCourse(courseid);
		c.setStudents(students);
		return ResponseEntity.ok(courseRepository.save(c));
	}

	public Course findCourse(Long id) throws CourseDoesNotExist {
		Course course = courseRepository.findOne(id);
		if (course != null) {
			return course;
		}
		throw new CourseDoesNotExist("Course of id = " + id + " does not exist.");
	}

	public List<Student> getCoursesStudents(Map<String, String> my_map) throws CourseDoesNotExist {
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= my_map.size(); i++) {
			Long id1 = Long.valueOf(my_map.get("course_id" + i));
			students.addAll(getCourseStudents(id1));

		}
		return students;
	}

}
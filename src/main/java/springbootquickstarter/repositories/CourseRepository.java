package springbootquickstarter.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springbootquickstarter.entities.Course;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository <Course, Long>{
	public List <Course> findByTopicId(Long topicId);
	
}


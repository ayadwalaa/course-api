package springbootquickstarter.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springbootquickstarter.entities.Topic;

@Repository
public interface TopicRepository extends CrudRepository <Topic, Long>{
	

}

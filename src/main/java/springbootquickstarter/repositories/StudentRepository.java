package springbootquickstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootquickstarter.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
}

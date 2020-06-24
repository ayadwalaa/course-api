package springbootquickstarter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication 
@Component
public class CourseAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(CourseAPIApp.class, args);
		// This static method starts the server (Tomcat), sets up the configuration and scans the class path

	}

}

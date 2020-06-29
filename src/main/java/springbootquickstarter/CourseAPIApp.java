package springbootquickstarter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication 
@Component
public class CourseAPIApp {
	public static void main(String[] args) {
		SpringApplication.run(CourseAPIApp.class, args);
	}

}

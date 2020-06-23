package springbootquickstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(CourseAPIApp.class, args);
		// This static method starts the server (Tomcat), sets up the configuration and scans the class path

	}

}

package springbootquickstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@Component
@Configuration
public class CourseAPIApp {
	public static void main(String[] args) {
		SpringApplication.run(CourseAPIApp.class, args);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}

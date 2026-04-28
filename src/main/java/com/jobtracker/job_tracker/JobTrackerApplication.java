package com.jobtracker.job_tracker;

// SpringApplication is the class that starts the entire Spring Boot app
import org.springframework.boot.SpringApplication;

// @SpringBootApplication is the master annotation that:
// 1. @Configuration — marks this as a config class
// 2. @EnableAutoConfiguration — turns on Spring Boot's auto-config
// 3. @ComponentScan — tells Spring to scan for all @Service, @Repository,
//    @Controller classes — BUT it only scans the current package and sub-packages
//    So we need to tell it to ALSO scan com.jobtracker.jobtracker
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scanBasePackages tells Spring Boot to scan BOTH packages:
// - com.jobtracker.job_tracker (where main class is)
// - com.jobtracker.jobtracker (where your controller/service/repo are)
@SpringBootApplication
public class JobTrackerApplication {

	public static void main(String[] args) {
		// This one line starts the ENTIRE application:
		// - Starts embedded Tomcat server on port 8080
		// - Connects to MySQL database
		// - Creates all beans (Service, Repository, Controller)
		// - Maps all URL endpoints
		SpringApplication.run(JobTrackerApplication.class, args);
	}
}
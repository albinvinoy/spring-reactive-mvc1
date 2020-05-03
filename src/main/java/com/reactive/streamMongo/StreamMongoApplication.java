package com.reactive.streamMongo;

import com.reactive.streamMongo.model.Employee;
import com.reactive.streamMongo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.management.MXBean;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamMongoApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository) {
		return args -> {
			employeeRepository.deleteAll()
					.subscribe(null, null, () -> {
						Stream.of(new Employee(UUID.randomUUID().toString(), "Peter", 243000),
								new Employee(UUID.randomUUID().toString(), "Mele", 249000),
								new Employee(UUID.randomUUID().toString(), "Sarah", 343000),
								new Employee(UUID.randomUUID().toString(), "Jack", 70000),
								new Employee(UUID.randomUUID().toString(), "Mike", 236000)
						).forEach(employee -> {
							employeeRepository.save(employee)
									.subscribe(emp -> System.out.println(emp));
						});
					});
		};
	}


	public static void main(String[] args) {

		SpringApplication.run(StreamMongoApplication.class, args);
	}

}

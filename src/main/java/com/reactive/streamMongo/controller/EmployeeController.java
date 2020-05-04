//package com.reactive.streamMongo.controller;
//
//import com.reactive.streamMongo.model.Employee;
//import com.reactive.streamMongo.model.EmployeeEvent;
//import com.reactive.streamMongo.repository.EmployeeRepository;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.util.function.Tuple2;
//import sun.util.calendar.LocalGregorianCalendar;
//
//import java.awt.*;
//import java.time.Duration;
//import java.util.Date;
//import java.util.stream.Stream;
//
//@RestController
//@RequestMapping("/rest/employee")
//public class EmployeeController {
//
//	private final EmployeeRepository employeeRepository;
//
//	public EmployeeController(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
//
//	@GetMapping("/all")
//	public Flux<Employee> getAllEmployee() {
//		return employeeRepository.findAll();
//	}
//
//	@GetMapping("/{id}")
//	public Mono<Employee> getId(@PathVariable("id") final String id) {
//		return employeeRepository.findById(id);
//	}
//
//	@GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String id) {
//		return employeeRepository.findById(id)
//				.flatMapMany(employee -> {
//					Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
//
//					Flux<EmployeeEvent> employeeEventFlux =
//							Flux.fromStream(
//									Stream.generate(() -> new EmployeeEvent(employee, new Date()))
//							);
//					return Flux.zip(interval, employeeEventFlux)
//							.map(Tuple2::getT2);
//
//				});
//	}
//}

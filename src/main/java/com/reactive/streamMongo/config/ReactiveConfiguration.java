package com.reactive.streamMongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ReactiveConfiguration {

	@Bean
	RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
		return RouterFunctions.route(
				RequestPredicates.GET("/rest/employee/all"), routerHandlers::getAll)
				.andRoute(RequestPredicates.GET("rest/employee/{id}"), routerHandlers::getId)
				.andRoute(RequestPredicates.GET("rest/employee/{id}/events"), routerHandlers::getEvents);
	}
}

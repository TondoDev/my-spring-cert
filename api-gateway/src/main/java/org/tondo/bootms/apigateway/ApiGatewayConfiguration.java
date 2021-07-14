package org.tondo.bootms.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	
	@Bean
	public RouteLocator getewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(routeFunction -> routeFunction.path("/currency-exchange/**")
						// lb means use load balancer
						.uri("lb://currency-exchange"))
				.build();
	}
}

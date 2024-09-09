package com.ahamdah.ahamdahkeycloakgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AhamdahKeycloakGatewayApplication {

    @Autowired
    private AuthorizationGatewayFilter authorizationGatewayFilter;

    public static void main(String[] args) {
        SpringApplication.run(AhamdahKeycloakGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hr", r -> r.path("/**")
                        .filters(f -> f.filter(authorizationGatewayFilter))
                        .uri("lb://INSPIRE-SYSTEM"))
                .build();
    }
}

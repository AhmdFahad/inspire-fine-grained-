package com.ahamdah.config;

import com.ahamdah.filter.AuthorizationGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {

    private AuthorizationGatewayFilter authorizationGatewayFilter;

    public Config(AuthorizationGatewayFilter authorizationGatewayFilter) {
        this.authorizationGatewayFilter = authorizationGatewayFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hr", r -> r.path("/hr/**")
                        .filters(f -> f.filter(authorizationGatewayFilter))
                        .uri("lb://TEST"))
                .route("other service", r -> r.path("/ahmed")
                        .filters(f -> f.filter(authorizationGatewayFilter))
                        .uri("lb://TEST"))
                .build();
    }
}

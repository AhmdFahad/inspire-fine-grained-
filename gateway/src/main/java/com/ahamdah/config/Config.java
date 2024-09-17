package com.ahamdah.config;

import com.ahamdah.filter.AuthorizationErpGatewayFilter;
import com.ahamdah.filter.AuthorizationGatewayFilter;
import com.ahamdah.filter.LoggerFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {

    private AuthorizationGatewayFilter authorizationGatewayFilter;
    private AuthorizationErpGatewayFilter authorizationErpGatewayFilter;
    private LoggerFilter loggerFilter;

    public Config(AuthorizationGatewayFilter authorizationGatewayFilter, AuthorizationErpGatewayFilter authorizationErpGatewayFilter, LoggerFilter loggerFilter) {
        this.authorizationGatewayFilter = authorizationGatewayFilter;
        this.authorizationErpGatewayFilter = authorizationErpGatewayFilter;
        this.loggerFilter = loggerFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("erp-portal", r -> r.path("/api/**")
                        .filters(
                                f -> f.filter(loggerFilter)
                                        .filter(authorizationErpGatewayFilter)
                        )
                        .uri("https://erp-qa.inspirejo.com"))
                .build();
    }
}

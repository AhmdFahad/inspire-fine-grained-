package com.ahamdah.ahamdahkeycloakgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationGatewayFilter implements GatewayFilter {


    private final WebClient webClient = WebClient.create();
    private static final Logger log = LoggerFactory.getLogger(AuthorizationGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String resource = exchange.getRequest().getPath().value();
        HttpMethod method = exchange.getRequest().getMethod();

        log.info("Resource: {}", resource);
        log.info("HTTP Method: {}", method);

        return webClient.method(method)
                .uri("http://localhost:8082" + resource)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return chain.filter(exchange);
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                });
    }
}

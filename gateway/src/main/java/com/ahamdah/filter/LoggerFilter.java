package com.ahamdah.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Slf4j
public class LoggerFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Request Method: {}", exchange.getRequest().getMethod());
        log.info("Request URI: {}", exchange.getRequest().getURI());
        log.info("Request Path: {}", exchange.getRequest().getURI().getPath());
        log.info("Request Time: {}", LocalDateTime.now());
        return chain.filter(exchange);
    }
}

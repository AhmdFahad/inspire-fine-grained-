package com.ahamdah;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${eureka.client.enabled}")
    private String eurekaClientEnabled;

    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String defaultZone;

    @Override
    public void run(String... args) throws Exception {
      log.info("active Profile: {}", profile);
      log.info("eurekaClientEnabled: {}", eurekaClientEnabled);
      log.info("defaultZone: {}", defaultZone);
    }
}

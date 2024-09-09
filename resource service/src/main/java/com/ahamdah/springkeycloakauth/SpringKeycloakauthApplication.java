package com.ahamdah.springkeycloakauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringKeycloakauthApplication {
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringKeycloakauthApplication.class, args);
	}




}

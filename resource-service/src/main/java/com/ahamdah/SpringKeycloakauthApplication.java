package com.ahamdah;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@SpringBootApplication
public class SpringKeycloakauthApplication implements CommandLineRunner {

	Logger logger = Logger.getLogger(SpringKeycloakauthApplication.class.getName());

	@Value("${server.port}")
	private String port;
	@Value("${spring.profiles.active}")
	private String activeProfile;
	@Value("${keycloak.policy-enforcer.realm}")
	private String realm;
	@Override
	public void run(String... args) throws Exception {
		logger.info("Server started at port " + port);
		logger.info("Active profile: " + activeProfile);
		logger.info("realm: " + realm);


	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKeycloakauthApplication.class, args);
	}

}

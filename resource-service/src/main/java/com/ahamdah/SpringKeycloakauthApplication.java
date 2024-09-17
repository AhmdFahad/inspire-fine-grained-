package com.ahamdah;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@SpringBootApplication
public class SpringKeycloakauthApplication implements CommandLineRunner {
	Logger logger = Logger.getLogger(SpringKeycloakauthApplication.class.getName());


	@Override
	public void run(String... args) throws Exception {
		logger.info("Hello World");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKeycloakauthApplication.class, args);
	}

}

package com.ahamdah.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TestApplication {

    @GetMapping("/hr/")
    private String hello() {
        return "hello";
    }
    @GetMapping("/hr/{id}")
    private String hello(@PathVariable String id) {
        return "hello : "+ id;
    }
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}

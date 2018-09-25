package com.kimremain.apipractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.kimremain"})
@EntityScan("com.kimremain")
public class ApiPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPracticeApplication.class, args);
	}
}

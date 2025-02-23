package com.basicapp.basicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BasicappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicappApplication.class, args);
	}

}

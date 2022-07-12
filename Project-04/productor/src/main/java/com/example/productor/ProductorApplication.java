package com.example.productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.productor.controller",
                                           "com.example.productor.service",
                                           "com.example.productor.model"})
public class ProductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductorApplication.class, args);
	}

}

package com.example.scooterrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.scooterrental.controller"})
public class ScooterRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScooterRentalApplication.class, args);
	}

}

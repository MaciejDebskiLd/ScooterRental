package com.example.scooterrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
@ComponentScan(basePackages = {"com.example.scooterrental.controller",
 "com.example.scooterrental.common",
 "com.example.scooterrental.repository",
 "com.example.scooterrental.model",
"com.example.scooterrental.service"})
public class ScooterRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScooterRentalApplication.class, args);
	}

}

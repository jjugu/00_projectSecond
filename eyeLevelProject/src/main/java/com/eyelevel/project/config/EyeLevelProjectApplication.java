package com.eyelevel.project.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eyelevel.project"})
public class EyeLevelProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EyeLevelProjectApplication.class, args);
	}
}
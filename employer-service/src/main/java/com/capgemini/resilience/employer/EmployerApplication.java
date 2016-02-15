package com.capgemini.resilience.employer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class EmployerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployerApplication.class, args);
	}
}

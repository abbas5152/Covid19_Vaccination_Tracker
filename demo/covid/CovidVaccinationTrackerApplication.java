package com.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CovidVaccinationTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidVaccinationTrackerApplication.class, args);
	}

}

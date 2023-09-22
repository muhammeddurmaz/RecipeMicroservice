package com.Recipe.recipesearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RecipeSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeSearchServiceApplication.class, args);
	}

}

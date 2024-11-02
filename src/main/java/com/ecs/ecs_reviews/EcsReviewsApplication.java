package com.ecs.ecs_reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcsReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcsReviewsApplication.class, args);
	}

}

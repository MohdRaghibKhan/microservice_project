package com.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.userservice.entities.Rating;
import com.userservice.entities.Rating.RatingBuilder;
import com.userservice.external.services.RatingService;

@SpringBootTest(classes = UserServiceApplication.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
	@Test
	void createRating() {
		Rating ratingBuilder = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using feign client 2").build();
		Rating createRating = ratingService.createRating(ratingBuilder);
		System.out.println("new rating created"+createRating);
	}

}

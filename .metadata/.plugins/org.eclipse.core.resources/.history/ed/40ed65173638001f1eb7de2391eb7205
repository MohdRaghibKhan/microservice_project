package com.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.userservice.entities.Rating;
@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	@PostMapping("/ratings/")
	Rating createRating(Rating rating);
	
	@GetMapping("/ratings/{ratingId}")
	Rating updateRating(@RequestBody Rating rating,@PathVariable String ratingId);
//	@DeleteMapping()
//	void deleteRating(@PathVariable String ratingId);
	
	
	
}

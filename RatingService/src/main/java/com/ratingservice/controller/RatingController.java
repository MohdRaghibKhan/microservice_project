package com.ratingservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.entities.Rating;
import com.ratingservice.repositories.RatingRepository;
import com.ratingservice.service.RatingService;
import com.ratingservice.status.ValidationResponse;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	RatingService ratingService;
	@Autowired
	RatingRepository ratingRepository;
	
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		String randomUid = UUID.randomUUID().toString();
		rating.setRattingId(randomUid);
		return  ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Rating>> getAllRating() {
		return  ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRating());
	}
	@PutMapping("/updateRatingByUId/{userId}")
	public ResponseEntity<?> updateRatings(@RequestBody Rating rating,@PathVariable String userId) {
		
		Optional<Rating> updateRating = ratingService.updateRating(rating,userId);
		
		if (updateRating.isEmpty()) {
			return ResponseEntity.ok(ValidationResponse.USERNOTEXTST);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ValidationResponse.RATINGUPDATED);
	}
	@GetMapping("/user/{uId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("uId") String uid) {
		return  ResponseEntity.status(HttpStatus.OK).body(ratingRepository.findByUserId(uid));
	}
	@GetMapping("/hotels/{hId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hId") String hid) {
		return  ResponseEntity.status(HttpStatus.OK).body(ratingRepository.findByHotelId(hid));
	}
}

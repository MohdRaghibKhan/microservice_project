package com.ratingservice.service;

import java.util.List;
import java.util.Optional;

import com.ratingservice.entities.Rating;

public interface RatingService {
	public Rating createRating(Rating rating);

	public List<Rating> getAllRating();

	public List<Rating> getAllHotel();

	public Optional<Rating> updateRating(Rating rating, String id);

	public String deleteRatingById(String id);

	public String deleteAllRating();

	public List<Rating> getRatingByUserId(String id);

	public List<Rating> getRatingByHotelId(String id);

}

package com.ratingservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.ratingservice.entities.Rating;

@EnableMongoRepositories
@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

	// custom finder methods
	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);

	Optional<Rating> findByUserIdAndHotelId(String userId,String hotelId);
}

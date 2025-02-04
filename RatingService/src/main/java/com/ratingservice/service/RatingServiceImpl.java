package com.ratingservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entities.Rating;
import com.ratingservice.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	ModelMapper mapper;
	
	@Override
	public Rating createRating(Rating rating) { // find rating by user id from DB

		Optional<Rating> ratingByUserAndHotel = ratingRepository.findByUserIdAndHotelId(rating.getUserId(),
				rating.getHotelId());
		Rating existingRating;
		if (!ratingByUserAndHotel.isEmpty()) {
			rating.setRattingId(ratingByUserAndHotel.get().getRattingId());
			existingRating = ratingByUserAndHotel.get();
			mapper.map(rating, existingRating);
			return ratingRepository.save(existingRating);
		} else {
			return ratingRepository.save(rating);
		}
	}

	@Override
	public List<Rating> getAllRating() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public Optional<Rating> updateRating(Rating rating, String userId) {
		List<Rating> ratingById = ratingRepository.findByUserId(userId);
		List<Rating> HotelById = ratingRepository.findByHotelId(rating.getHotelId());
		Optional<Rating> ratingOfUser = ratingById.stream()
				.filter(f -> f.getUserId().equals(rating.getUserId()) && f.getHotelId().equals(rating.getHotelId()))
				.findFirst();
		if (!ratingOfUser.isEmpty()) {
			ratingRepository.save(rating);
			return Optional.of(rating);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public String deleteRatingById(String id) {
		ratingRepository.deleteById(id);
		Rating rating = ratingRepository.findById(id).get();
		if (rating == null) {
			return "deleted successfully";
		} else {
			return "deletion failed";
		}
	}

	@Override
	public String deleteAllRating() {
		// TODO Auto-generated method stub
		ratingRepository.deleteAll();
		List<Rating> ratings = ratingRepository.findAll();
		if (ratings.size() == 0) {
			return "deleted successfully";
		} else {
			return "deletion failed";
		}
	}

	@Override
	public List<Rating> getAllHotel() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByHotelId(String id) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(id);
	}

	@Override
	public List<Rating> getRatingByUserId(String id) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(id);
	}

}

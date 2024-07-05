package com.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.entities.Hotel;
import com.userservice.entities.Rating;
import com.userservice.entities.User;
import com.userservice.exception.ResourceNotFoundEXception;
import com.userservice.external.services.HotelService;
import com.userservice.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;
	@Autowired
	RestTemplate restTemplate;
	private Logger logger=LoggerFactory.getLogger(UserService.class);
	@Autowired
	private HotelService hotelService;
	
	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return repository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		// get user with the help of user repository
		User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundEXception("Resource not found"));
//		fetching rating of the above user from rating service
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
//		ratings.stream().map(rating -> {
//																	BY USING REST TEMPLATE
//			 api call to hotel service to get hotel
//			http://localhost:7979/hotels/get/fafd6aab-f1a2-457b-9ca1-46f4a8e2eab1
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/get/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			logger.info("response status code: {}", forEntity.getStatusCode());
//			// set the hotel to rating
//			rating.setHotel(hotel);
//			return rating;
//		}).collect(Collectors.toList());
//		user.setRatings(ratings);
//		logger.info("{} ", ratingOfUser);
//		return user;
//																 BY USING FEING CLIENT
		ratings.stream().map(rating -> {
//		 api call to hotel service to get hotel
//		http://localhost:7979/hotels/get/fafd6aab-f1a2-457b-9ca1-46f4a8e2eab1
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// set the hotel to rating
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratings);
		logger.info("{} ", ratingOfUser);
		return user;
	}

	@Override
	public String deleteUserById(String id) {
		repository.deleteById(id);
		User userById = getUserById(id);
		if(userById==null) {
			return "deleted successfully";
		}else {
			return "deletion failed";
		}

	}

	@Override
	public String deleteAllUser() {
		repository.deleteAll();
		List<User> userById = repository.findAll();
		if(userById.size()==0) {
			return "deleted successfully";
		}else {
			return "deletion failed";
		}
	}

	@Override
	public User updateUserById(User user,String id) {
//		User oldUser = repository.findById(id).get();
//		User user2 = new User(user.getUserId(),user.getName(),user.getEmail(),user.getAbout());
		return repository.save(user);
	}

}

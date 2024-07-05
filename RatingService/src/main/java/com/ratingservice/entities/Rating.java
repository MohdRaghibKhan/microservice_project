package com.ratingservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("microservice")
public class Rating {
	@Id
	private String rattingId;
	private String userId;
	private String hotelId;
	private int ratings;
	private String feedback;

}

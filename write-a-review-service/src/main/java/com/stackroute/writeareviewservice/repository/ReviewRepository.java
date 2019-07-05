package com.stackroute.writeareviewservice.repository;

import com.stackroute.writeareviewservice.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review,String> {
}

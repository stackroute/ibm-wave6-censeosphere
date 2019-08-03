package com.stackroute.review.repository;

import com.stackroute.review.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review,String> {
    //get all reviews by specific product name
    @Query("{ 'productName' : ?0 }")
    List<Review> getAllReviewsbyProduct(String productname);


}

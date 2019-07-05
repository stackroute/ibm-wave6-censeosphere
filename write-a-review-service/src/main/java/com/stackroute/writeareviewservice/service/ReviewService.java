package com.stackroute.writeareviewservice.service;

import com.stackroute.writeareviewservice.domain.Review;

import java.util.List;

public interface ReviewService {
    public Review addReview(Review review);
    public List<Review> getAllReviews();
   // public List<Review> getReviewsByName();
}

package com.stackroute.review.service;

import com.stackroute.review.domain.Review;

import java.util.List;

public interface ReviewService {
    public Review addReview(Review review);
    public List<Review> getAllReviews();
}

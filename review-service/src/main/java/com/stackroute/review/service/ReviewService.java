package com.stackroute.review.service;

import com.stackroute.review.domain.Review;
import com.stackroute.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public Review addReview(Review review);
    public List<Review> getAllReviews();
    public void sendRating(ReviewDTO reviewDTO);
}

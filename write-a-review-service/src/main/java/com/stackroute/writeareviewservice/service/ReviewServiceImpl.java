package com.stackroute.writeareviewservice.service;

import com.stackroute.writeareviewservice.domain.Review;
import com.stackroute.writeareviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }
    @Override
    public Review addReview(Review review) {

        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}

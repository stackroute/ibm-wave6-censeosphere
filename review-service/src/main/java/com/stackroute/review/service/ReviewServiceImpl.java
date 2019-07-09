package com.stackroute.review.service;

import com.stackroute.review.domain.Review;
import com.stackroute.review.repository.ReviewRepository;
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

        //rabbitmq queue, exchange,routingkey generation
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }



}

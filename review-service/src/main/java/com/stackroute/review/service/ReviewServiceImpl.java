package com.stackroute.review.service;

import com.stackroute.review.domain.Review;
import com.stackroute.review.dto.RecommendationDTO;
import com.stackroute.review.dto.ReviewDTO;
import com.stackroute.review.repository.ReviewRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeythree}")
    private String routingkeythree;

    @Value("${stackroute.rabbitmq.routingkeyfive}")
    private String routingkeyfive;

    @Value("${stackroute.rabbitmq.routingkeyseven}")
    private String routingkeyseven;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }

    @Override
    public Review addReview(Review review) {
             Review savedReview=null;
           if(((review.getReviewDescription()).length())>=10) {
             savedReview = reviewRepository.save(review);
             ReviewDTO reviewDTO = new ReviewDTO(review.getProductName(), review.getReviewDescription(), review.getCreditpoints());
             sendRating(reviewDTO);
             sendReviewer(savedReview);
             RecommendationDTO recommendationDTO = new RecommendationDTO(review.getReviewerEmail(), review.getProductName(), review.getSubCategory());
             sendRecommendation(recommendationDTO);

             }
        return savedReview;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }


    @Override
    public List<Review> getAllReviewsbyProduct(String pname)
    {

        return reviewRepository.getAllReviewsbyProduct(pname);

    }
    @Override
    public void sendRating(ReviewDTO reviewDTO)
    {
        rabbitTemplate.convertAndSend(exchange, routingkeythree, reviewDTO);
    }

    @Override
    public void sendReviewer(Review review)
    {
        rabbitTemplate.convertAndSend(exchange, routingkeyfive, review);
    }

    @Override
    public void sendRecommendation(RecommendationDTO recommendationDTO) {
        rabbitTemplate.convertAndSend(exchange, routingkeyseven, recommendationDTO);
    }
}

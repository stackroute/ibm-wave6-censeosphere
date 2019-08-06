package com.stackroute.review.service;

import com.netflix.discovery.converters.Auto;
import com.stackroute.review.domain.FakeReview;
import com.stackroute.review.domain.Review;
import com.stackroute.review.dto.RecommendationDTO;
import com.stackroute.review.dto.ReviewDTO;
import com.stackroute.review.repository.FakeReviewRepository;
import com.stackroute.review.repository.ReviewRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = {"review"})
@Service
public class ReviewServiceImpl implements ReviewService {


    private ReviewRepository reviewRepository;
    private FakeReviewRepository fakeReviewRepository;


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
    public ReviewServiceImpl(ReviewRepository reviewRepository,FakeReviewRepository fakeReviewRepository)
    {
        this.reviewRepository=reviewRepository;
        this.fakeReviewRepository = fakeReviewRepository;
    }

    private String status="invalid";

    //service implemetation to save reviews given by reviewer
    @CacheEvict(allEntries = true)
    @Override
    public Review addReview(Review review) {
        Review savedReview=null;
        if(((review.getReviewDescription()).length())>=20) {
            savedReview = reviewRepository.save(review);
            ReviewDTO reviewDTO = new ReviewDTO(review.getProductName(), review.getReviewDescription(), review.getCreditpoints());
            sendRating(reviewDTO);
            sendReviewer(savedReview);
            RecommendationDTO recommendationDTO = new RecommendationDTO(review.getReviewerEmail(), review.getProductName(), review.getSubCategory());
            sendRecommendation(recommendationDTO);
           }
        else
        {
            FakeReview fakeReview=new FakeReview();

            fakeReview.setReviewerMail(review.getReviewerEmail());
            fakeReview.setReviewTitle(review.getReviewTitle());
            fakeReview.setReviewDescription(review.getReviewDescription());
            fakeReview.setProductName(review.getProductName());
            fakeReview.setPrice(review.getPrice());
            fakeReview.setReviewedOn(review.getReviewedOn());
            fakeReview.setSubCategory(review.getSubCategory());
            fakeReview.setCreditpoints(review.getCreditpoints());
            fakeReview.setStatus(status);
            fakeReviewRepository.save(fakeReview);

        }
        return savedReview;
    }

    //service implemenation to get all reviewes
    @Cacheable(value="review")
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    //service implementation to get all reviews by specific product name
    @Override
    public List<Review> getAllReviewsbyProduct(String productname) {
        return reviewRepository.getAllReviewsbyProduct(productname);
    }

    //service implementation to send rating through rabbitmq
    @Override
    public void sendRating(ReviewDTO reviewDTO)
    {

        System.out.println("send to nlp "+reviewDTO);
        rabbitTemplate.convertAndSend(exchange, routingkeythree, reviewDTO);
    }

    //service implentation to send reviewer through rabbitmq
    @Override
    public void sendReviewer(Review review)
    {

        System.out.println("send to reviewer profile"+ review);
        rabbitTemplate.convertAndSend(exchange, routingkeyfive, review);
    }

    //service implementation to send data to recommendation service
    @Override
    public void sendRecommendation(RecommendationDTO recommendationDTO) {
        System.out.println("send to recommendation "+recommendationDTO);
        rabbitTemplate.convertAndSend(exchange, routingkeyseven, recommendationDTO);
    }
}

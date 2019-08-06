package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.dto.ReviewDTO;
import com.stackroute.recommendation.exception.ReviewerNotFoundException;
import com.stackroute.recommendation.repository.ReviewerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReviewerServiceImpl implements ReviewerService {
    private ReviewerRepository reviewerRepository;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    @Value("${reviewerNotFound}")
    String reviewerNotFound;

    //service implementation to save reviewer
    @Override
    public Reviewer saveReviewer(Reviewer reviewer) {
        Reviewer  savedReviewer=null;
        savedReviewer=reviewerRepository.createNode(reviewer.getEmailId());
        return savedReviewer;
    }

    //service implementation to get all reviewer
    @Override
    public Collection<Reviewer> getAll() throws ReviewerNotFoundException {
        Collection<Reviewer> reviewerCollection=reviewerRepository.getAllReviewers();
        if(reviewerCollection.isEmpty()){
            throw  new ReviewerNotFoundException(reviewerNotFound);
        }
        return reviewerRepository.getAllReviewers();
    }

    //service implementation to delete reviewer
    @Override
    public void deleteReviewer(String emailId) throws ReviewerNotFoundException {
        if(reviewerRepository.existsById(emailId)) {
            reviewerRepository.deleteNode(emailId);
        }
        else {
            throw new ReviewerNotFoundException(reviewerNotFound);
        }
    }

    //service implementation to get reviewer
    @Override
    public Reviewer getByName(String emailId) throws ReviewerNotFoundException{
        if(reviewerRepository.existsById(emailId)) {
            return reviewerRepository.getNode(emailId);
        }
        else {
            throw new ReviewerNotFoundException(reviewerNotFound);
        }
    }

    //service implementation to create relation between reviewer and product
    @Override
    public Reviewer saveRelation(String emailId, String productName) {
        return reviewerRepository.createRelation(emailId,productName);
    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueseven}")
    public void  recievereviw(ReviewDTO reviewDTO) {
        System.out.println("Inside recommendation review "+reviewDTO);
        Reviewer reviewer1=new Reviewer(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        if(reviewerRepository.existsById(reviewDTO.getReviewerEmail())){
            reviewerRepository.createRelation(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        }
        else {
            reviewerRepository.createNode(reviewer1.getEmailId());
            reviewerRepository.createRelation(reviewer1.getEmailId(),reviewer1.getProductName());
        }
    }
}

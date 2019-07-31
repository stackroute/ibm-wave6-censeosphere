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
    @Override
    public Reviewer saveReviewer(Reviewer reviewer) {
        Reviewer  savedReviewer=null;
        savedReviewer=reviewerRepository.createNode(reviewer.getEmailId());
        return savedReviewer;
    }

    @Override
    public Collection<Reviewer> getAll() throws ReviewerNotFoundException {
        Collection<Reviewer> reviewerCollection=reviewerRepository.getAllReviewers();
        if(reviewerCollection.isEmpty()){
            throw  new ReviewerNotFoundException(reviewerNotFound);
        }
        return reviewerRepository.getAllReviewers();
    }

    @Override
    public void deleteReviewer(String emailId) throws ReviewerNotFoundException {
        if(reviewerRepository.existsById(emailId)) {
            reviewerRepository.deleteNode(emailId);
        }
        else
        {
            throw new ReviewerNotFoundException(reviewerNotFound);
        }
    }

    @Override
    public Reviewer getByName(String emailId) throws ReviewerNotFoundException{
        if(reviewerRepository.existsById(emailId)) {
            return reviewerRepository.getNode(emailId);
        }
        else {
            throw new ReviewerNotFoundException(reviewerNotFound);
        }
    }

    @Override
    public Reviewer saveRelation(String emailId, String productName) {
        return reviewerRepository.createRelation(emailId,productName);
    }



    @RabbitListener(queues="${stackroute.rabbitmq.queueseven}")
    public void  recievereviw(ReviewDTO reviewDTO) {
        Reviewer reviewer1=new Reviewer(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        if(reviewerRepository.existsById(reviewDTO.getReviewerEmail())){
            saveRelation(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        }
        else {
            saveReviewer(reviewer1);
            saveRelation(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        }
    }
}

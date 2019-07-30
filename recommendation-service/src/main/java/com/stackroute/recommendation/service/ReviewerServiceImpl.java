package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.dto.ReviewDTO;
import com.stackroute.recommendation.exception.ReviewerNotFoundException;
import com.stackroute.recommendation.repository.ReviewerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReviewerServiceImpl implements ReviewerService {
    private ReviewerRepository reviewerRepository;

    private Reviewer reviewer;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    @Override
    public Reviewer saveReviewer(Reviewer reviewer) {

        Reviewer  savedReviewer=null;

        savedReviewer=reviewerRepository.createNode(reviewer.getEmailId());

        System.out.println(savedReviewer);
        return savedReviewer;
    }

    @Override
    public Collection<Reviewer> getAll() throws Exception {
        Collection<Reviewer> reviewerCollection=reviewerRepository.getAllReviewers();
        if(reviewerCollection.isEmpty()){
            throw  new Exception("Reviewer not available");
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
            throw new ReviewerNotFoundException("Reviewer Not Found");
        }
    }

    @Override
    public Reviewer getByName(String emailId) throws ReviewerNotFoundException{
        if(reviewerRepository.existsById(emailId)) {
            return reviewerRepository.getNode(emailId);
        }
        else {
            throw new ReviewerNotFoundException("Reviewer Not Found");
        }
    }

    @Override
    public Reviewer saveRelation(String emailId, String productName) {
        return reviewerRepository.createRelation(emailId,productName);
    }



    @RabbitListener(queues="${stackroute.rabbitmq.queueseven}")
    public void  recievereviw(ReviewDTO reviewDTO) {

        System.out.println("recieved msg  from write a review= " + reviewDTO.toString());
        Reviewer reviewer=new Reviewer(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
        if(reviewerRepository.existsById(reviewDTO.getReviewerEmail())){
            saveRelation(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
            System.out.println("Relation created old new reviewer");
        }
        else {
            saveReviewer(reviewer);
            saveRelation(reviewDTO.getReviewerEmail(),reviewDTO.getProductName());
            System.out.println("Relation created for new reviewer");
        }
    }
}

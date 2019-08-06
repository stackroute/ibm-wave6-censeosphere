package com.stackroute.reviewerprofile.service;

import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.domain.Review;
import com.stackroute.reviewerprofile.dto.ReviewerDTO;
import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;
import com.stackroute.reviewerprofile.repository.ReviewerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewerServiceImpl implements ReviewerService {
    private ReviewerRepository reviewerRepository;
    Reviewer reviewer=null;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeyone}")
    private String routingkeyone;


    int   point;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewerRepository)
    {
        this.reviewerRepository=reviewerRepository;
    }

    //service implementation to save reviewer profile
    @Override
    public Reviewer saveReviewers(Reviewer reviewer) throws ReviewerAlreadyExistsException {
         if(reviewerRepository.existsById(reviewer.getEmailId())) {
            throw new ReviewerAlreadyExistsException("Reviewer already exists");
         }
         List<Review> myreviews=new ArrayList<Review>();
         reviewer.setRevieweswritten(myreviews);
         Reviewer savedReviewer=reviewerRepository.save(reviewer);
         ReviewerDTO reviewerDTO=new ReviewerDTO(reviewer.getEmailId(),reviewer.getReconfirmPassword(),reviewer.getRole());
         sendreviewer(reviewerDTO);
         return savedReviewer;
     }

     //service implementation to get all reviewers
    @Override
    public List<Reviewer> displayAllReviewers() {
        return reviewerRepository.findAll();
    }

    //service implementation to get all reviewer by emailId
    @Override
    public Reviewer getReviewerByEmailId(String emailId) throws ReviewerNotFoundException{
         Reviewer foundReviewer=null;
         if(reviewerRepository.existsById(emailId)){
             Optional optional=reviewerRepository.findById(emailId);
             if(optional.isPresent()) {
                 foundReviewer = (Reviewer) optional.get();
             }
         }
         else {
             throw new ReviewerNotFoundException("Reviewer Not Found");
         }
         return foundReviewer;
     }

     //service implementation to delete reviewer by emailId
    @Override
    public List<Reviewer> deleteReviewer(String emailId){
        Optional optional=reviewerRepository.findById(emailId);
        if(optional.isPresent()){
            reviewerRepository.deleteById(emailId);
        }
        else {
            try {
                throw new ReviewerNotFoundException("Reviewer Not Found");
            } catch (ReviewerNotFoundException e) {
                e.printStackTrace();
            }
        }
        return reviewerRepository.findAll();
    }

    //service implementation to update reviewer profile
    @Override
    public Reviewer updateReviewer(Reviewer reviewer,String emailId) throws ReviewerNotFoundException{
        Reviewer reviewer1=null;
        Optional optional;
        optional=reviewerRepository.findById(emailId);
        if(optional.isPresent())
        {
            reviewer1=reviewerRepository.findById(emailId).get();

            reviewer1.setName(reviewer.getName());
            reviewer1.setImage(reviewer.getImage());
            reviewer1.setReconfirmPassword(reviewer.getReconfirmPassword());
            reviewer1.setCreditpoints(reviewer.getCreditpoints());
            reviewerRepository.save(reviewer1);
            ReviewerDTO reviewerDTO1=new ReviewerDTO(reviewer1.getEmailId(),reviewer1.getReconfirmPassword(),reviewer1.getRole());
            sendreviewer(reviewerDTO1);

        }
        else
        {
            throw new ReviewerNotFoundException("Details not found");
        }

        return reviewer1;
    }


    //method to send reviewer through rabbitmq
    @Override
    public void sendreviewer(ReviewerDTO reviewerDTO) {
        System.out.println("send to user login "+reviewerDTO);
        rabbitTemplate.convertAndSend(exchange, routingkeyone, reviewerDTO);
    }

    @RabbitListener(queues="${stackroute.rabbitmq.queuefive}")
    public  void recievereview(Review review) {
        System.out.println("from review service "+review);
         Optional optional;
         Reviewer reviewer1;
         optional=reviewerRepository.findById(review.getReviewerEmail());
         List<Review> myreviewes;
         if(optional.isPresent()) {
             reviewer1=reviewerRepository.findById(review.getReviewerEmail()).get();
             myreviewes =reviewer1.getRevieweswritten();
             myreviewes.add(review);
             reviewer1.setRevieweswritten(myreviewes);
             point=reviewer1.getCreditpoints();
             point=reviewer1.getCreditpoints();
             point=point+5;
             reviewer1.setCreditpoints(point);
             reviewerRepository.save(reviewer1);
         }
     }
}

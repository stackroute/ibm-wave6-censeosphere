package com.stackroute.reviewerprofile.service;

import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.domain.Review;
//clear

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
    ReviewerRepository reviewerRepository;
    Reviewer reviewer=null;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeyone}")
    private String routingkeyone;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewerRepository)
    {
        this.reviewerRepository=reviewerRepository;
    }

    @Override
    public Reviewer saveReviewers(Reviewer reviewer) throws ReviewerAlreadyExistsException
    {
        if(reviewerRepository.existsById(reviewer.getEmailId()))
        {
            throw new ReviewerAlreadyExistsException("Reviewer already exists");
        }
        Reviewer savedReviewer=reviewerRepository.save(reviewer);
        ReviewerDTO reviewerDTO=new ReviewerDTO(reviewer.getEmailId(),reviewer.getReconfirmPassword(),reviewer.getRole());
         sendreviewer(reviewerDTO);
        if(savedReviewer==null)
        {
            throw new ReviewerAlreadyExistsException("Reviewer already exists");
        }

        return savedReviewer;
    }

    @Override
    public List<Reviewer> displayAllReviewers() {
        return reviewerRepository.findAll();
    }

    @Override
    public Reviewer getReviewerByEmailId(String emailId){
        Reviewer foundReviewer=null;
        if(reviewerRepository.existsById(emailId)){
            Optional optional=reviewerRepository.findById(emailId);
            foundReviewer=(Reviewer) optional.get();
        }
        else {
            try {
                throw new ReviewerNotFoundException("Reviewer Not Found");
            } catch (ReviewerNotFoundException e) {
                e.printStackTrace();
            }
        }
        return foundReviewer;
    }

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

    @Override
    public Reviewer updateReviewer(Reviewer reviewer,String emailId) throws ReviewerNotFoundException{
        Reviewer reviewer1=null;
        Optional optional;
        optional=reviewerRepository.findById(emailId);
        if(optional != null)
        {
           reviewer1=reviewerRepository.findById(emailId).get();

            System.out.println("from update method "+reviewer1);
            reviewer1.setName(reviewer.getName());
            reviewer1.setImage(reviewer.getImage());
            reviewer1.setReconfirmPassword(reviewer.getReconfirmPassword());

            System.out.println("After updating "+reviewer1);
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

    @Override
    public void sendreviewer(ReviewerDTO reviewerDTO)
    {

        rabbitTemplate.convertAndSend(exchange, routingkeyone, reviewerDTO);
        System.out.println("Send msg = " + reviewerDTO.toString());

    }

    @RabbitListener(queues="${stackroute.rabbitmq.queuefive}")
    public  void recievereview(Review review)
    {
        System.out.println("recieved msg from reviewer = " + review.toString());
//        Review review =new Review(reviewDetailDTO.getReviewerEmail(),reviewDetailDTO.getReviewTitle(),reviewDetailDTO.getReviewDescription(),reviewDetailDTO.getProductName(),reviewDetailDTO.getPrice(),reviewDetailDTO.getReviewedOn());
        System.out.println(review);
        Optional optional;
        Reviewer reviewer1;
        optional=reviewerRepository.findById(review.getReviewerEmail());
        List<Review> myreviewes;


        if(optional.isPresent())
        {
              System.out.println("hai");
              reviewer1=reviewerRepository.findById(review.getReviewerEmail()).get();
              System.out.println("Reviewer 1:"+reviewer1);

              myreviewes =reviewer1.getRevieweswritten();
              System.out.println("list "+myreviewes);
              myreviewes=new ArrayList<>();
              myreviewes.add(review);
              for (int i = 0; i < myreviewes.size(); i++) {
               System.out.println("inside list"+myreviewes.get(i));
              }
              reviewer1.setRevieweswritten(myreviewes);
              System.out.println(reviewer1);
              reviewerRepository.save(reviewer1);
        }
    }

}

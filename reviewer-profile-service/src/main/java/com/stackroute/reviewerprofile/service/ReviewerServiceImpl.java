package com.stackroute.reviewerprofile.service;

import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.dto.ReviewerDTO;
import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;
import com.stackroute.reviewerprofile.repository.ReviewerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public Reviewer updateReviewer(Reviewer reviewer){
        Reviewer reviewer1=null;
        if(reviewerRepository.existsById(reviewer.getEmailId())) {
            Optional optional = reviewerRepository.findById(reviewer.getEmailId());
            if (optional.isPresent()) {
                reviewer1 = (Reviewer) optional.get();
                reviewer1.setName(reviewer.getName());
                reviewer1 = reviewerRepository.save(reviewer1);
            }
        }
        else
        {
            try {
                throw new ReviewerNotFoundException("Details not found");
            } catch (ReviewerNotFoundException e) {
                e.printStackTrace();
            }
        }

        return reviewer1;
    }
//    @Override
//    public List<Reviewer> getAllReviews()
//    {
//        return reviewerRepository.findAll();
//    }
    @Override
    public void sendreviewer(ReviewerDTO reviewerDTO)
    {

        rabbitTemplate.convertAndSend(exchange, routingkeyone, reviewerDTO);
        System.out.println("Send msg = " + reviewerDTO.toString());

    }

}

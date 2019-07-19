package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.repository.ReviewerRepository;
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
    public Collection<Reviewer> getAll() {
        return reviewerRepository.getAllReviewers();
    }

    @Override
    public void deleteReviewer(String emailId) {
        reviewerRepository.deleteNode(emailId);
    }

    @Override
    public Reviewer getByName(String emailId) {
        return reviewerRepository.getNode(emailId);
    }

    @Override
    public Reviewer saveRelation(String emailId, String productName) {
        return reviewerRepository.createRelation(emailId,productName);
    }
}

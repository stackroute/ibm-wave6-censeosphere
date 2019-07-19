package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Reviewer;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface ReviewerService {
    public Reviewer saveReviewer(Reviewer reviewer);

    public Collection<Reviewer> getAll();

    public void deleteReviewer(String emailId);

    public Reviewer getByName(String emailId);

    public Reviewer saveRelation(String emailId,String productName);
}

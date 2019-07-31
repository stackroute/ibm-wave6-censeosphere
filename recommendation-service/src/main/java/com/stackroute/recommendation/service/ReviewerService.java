package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.exception.ReviewerNotFoundException;

import java.util.Collection;


public interface ReviewerService {
    public Reviewer saveReviewer(Reviewer reviewer) ;

    public Collection<Reviewer> getAll() throws ReviewerNotFoundException;

    public void deleteReviewer(String emailId) throws ReviewerNotFoundException;

    public Reviewer getByName(String emailId) throws ReviewerNotFoundException;

    public Reviewer saveRelation(String emailId,String productName);
}

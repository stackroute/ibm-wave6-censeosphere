package com.stackroute.reviewerprofile.service;

import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.dto.ReviewerDTO;
import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReviewerService {
    public Reviewer saveReviewers(Reviewer reviewer) throws ReviewerAlreadyExistsException;
    public List<Reviewer> displayAllReviewers() throws ReviewerNotFoundException;
    public Reviewer getReviewerByEmailId(String emailId) throws ReviewerNotFoundException;
    public List<Reviewer> deleteReviewer(String emailId) throws ReviewerNotFoundException;
    public Reviewer updateReviewer(Reviewer reviewer,String emailId) throws ReviewerNotFoundException;
    public void sendreviewer(ReviewerDTO reviewerDTO);
}

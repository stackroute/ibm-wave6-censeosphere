package com.stackroute.reviewerprofile.controller;

import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;
import com.stackroute.reviewerprofile.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/v1")
public class ReviewerController {

    @Autowired
    private ReviewerService reviewerService;

    public ReviewerController(ReviewerService reviewerService)

    {
        this.reviewerService=reviewerService;
    }

    @PostMapping("reviewer")
    public ResponseEntity<?> saveReviewers(@RequestBody Reviewer reviewer) throws ReviewerAlreadyExistsException   {
        ResponseEntity responseEntity;

            reviewerService.saveReviewers(reviewer);
            responseEntity=new ResponseEntity<String>("Reviewer successfully saved",HttpStatus.CREATED);

        return responseEntity;
    }

    @GetMapping("reviewer/{emailId}")
    public ResponseEntity<?> getReviewerByEmailId(@PathVariable("emailId") String emailId){


        try {
            Reviewer reviewer=reviewerService.getReviewerByEmailId(emailId);
            return new ResponseEntity<Reviewer>(reviewer, HttpStatus.OK);
        } catch (ReviewerNotFoundException e) {
            return new ResponseEntity<String>("Reviewer not found", HttpStatus.NOT_FOUND);
        }
       /* ResponseEntity responseEntity;
        Reviewer reviewer=reviewerService.getReviewerByEmailId(emailId);
        if(reviewer==null){
            throw new ReviewerNotFoundException("Reviewer Not Found");
        }
        else {
            responseEntity=new ResponseEntity<String>("Reviewer found",HttpStatus.OK);
        }
        return responseEntity;*/
    }

    @DeleteMapping("reviewer/{emailId}")
    public ResponseEntity<?> deleteReviewer(@PathVariable("emailId") String emailId) throws ReviewerNotFoundException {
        return new ResponseEntity<List<Reviewer>>(reviewerService.deleteReviewer(emailId),HttpStatus.OK);
    }


    @GetMapping("reviewers")
    public ResponseEntity<?> displayAllReviewers() throws ReviewerNotFoundException {
        return new ResponseEntity<List<Reviewer>>(reviewerService.displayAllReviewers(), HttpStatus.OK);
    }

    @PutMapping("reviewer/{emailId}")
    public ResponseEntity<?> updateReviewer(@RequestBody Reviewer reviewer,@PathVariable("emailId") String emailId) throws ReviewerNotFoundException {


       Reviewer reviewer1 = reviewerService.updateReviewer(reviewer,emailId);
        return new ResponseEntity<Reviewer>(reviewer, HttpStatus.OK);
    }
}
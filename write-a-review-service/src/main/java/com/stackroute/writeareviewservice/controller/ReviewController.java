package com.stackroute.writeareviewservice.controller;


import com.stackroute.writeareviewservice.domain.Review;
import com.stackroute.writeareviewservice.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestBody Review review)
    {
        ResponseEntity responseEntity;

            reviewService.addReview(review);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }



    @GetMapping("/getAllReviews")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<List<Review>> (reviewService.getAllReviews(),HttpStatus.OK);
    }
}



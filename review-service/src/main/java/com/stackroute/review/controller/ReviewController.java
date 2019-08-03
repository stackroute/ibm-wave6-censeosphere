package com.stackroute.review.controller;


import com.stackroute.review.domain.Review;
import com.stackroute.review.repository.ReviewRepository;
import com.stackroute.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/v1")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //method to save review given by reviewer
    @PostMapping(value = "/review")     //,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        ResponseEntity responseEntity;
        ReviewRepository reviewRepository;
        Date date=new Date();
        long millies=date.getTime();
        Timestamp timestamp=new Timestamp(millies);
        review.setReviewedOn(timestamp);
        reviewService.addReview(review);
        responseEntity=new ResponseEntity<Review>(review, HttpStatus.OK);
        return responseEntity;
    }

    //method to get all reviews
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<List<Review>> (reviewService.getAllReviews(),HttpStatus.OK);
    }

    //method to get all reviews by specific product name
    @GetMapping("/byname/{productname}")
    public ResponseEntity<List<Review>> getAllReviewsbyProduct(@PathVariable String productname) {
        return new ResponseEntity<List<Review>> (reviewService.getAllReviewsbyProduct(productname),HttpStatus.OK);
    }
}



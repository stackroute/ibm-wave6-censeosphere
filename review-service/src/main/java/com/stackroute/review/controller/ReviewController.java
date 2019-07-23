package com.stackroute.review.controller;


import com.stackroute.review.domain.Review;
import com.stackroute.review.repository.ReviewRepository;
import com.stackroute.review.service.ReviewService;
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

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/review")     //,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}
    public ResponseEntity<?> addReview(@RequestBody Review review)
    {
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

    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews() {
        return new ResponseEntity<List<Review>> (reviewService.getAllReviews(),HttpStatus.OK);
    }

    @GetMapping("/byname/{productname}")
    public ResponseEntity<?> getAllReviewsbyProduct(@PathVariable String productname) {
        return new ResponseEntity<List<Review>> (reviewService.getAllReviewsbyProduct(productname),HttpStatus.OK);
    }


}



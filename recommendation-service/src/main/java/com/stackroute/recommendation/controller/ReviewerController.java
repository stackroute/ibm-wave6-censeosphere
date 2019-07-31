package com.stackroute.recommendation.controller;

import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.exception.ReviewerNotFoundException;
import com.stackroute.recommendation.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "api/v1")
public class ReviewerController {


    private ReviewerService reviewerService;

    @Autowired
    public ReviewerController(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    @GetMapping("reviewers")
    public Collection<Reviewer> getAll() throws ReviewerNotFoundException
    {
        return reviewerService.getAll();
    }

    @PostMapping("reviewersave")
    public Reviewer saveReviewer(@RequestBody Reviewer reviewer) {
        Reviewer reviewer1=null;
        reviewer1=reviewerService.saveReviewer(reviewer);
        return reviewer1;

    }

    @GetMapping("emailid/{emailid}")
    public  Reviewer getReviewer(@PathVariable String emailid)throws ReviewerNotFoundException{
        return reviewerService.getByName(emailid);
    }

    @DeleteMapping("reviewerdelete/{emailid}")
    public String deleteReviewer(@PathVariable String emailid) throws ReviewerNotFoundException {
        reviewerService.deleteReviewer(emailid);
        return "Deleted Reviewer";
    }

    @PostMapping("graph/{emailid}/{productname}")
    public Reviewer saveRelation(@PathVariable String emailid,@PathVariable String productname){
        return  reviewerService.saveRelation(emailid,productname);
    }

}

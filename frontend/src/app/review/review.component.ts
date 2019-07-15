import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router } from '@angular/router';
import{ LandingpageService }from '../landingpage.service';
@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  reviews: any;

  constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService) { 
    this.reviews=[];
  }

  ngOnInit() {
    this.reviewService.addReview().subscribe((data:any)=>{
      console.log(data);
      this.reviews=data;
    })

    this.reviewService.getAllReviews().subscribe((data:any) => {
      console.log(data);
      this.reviews=data;
    })

  }
  
  onSubmit(reviewTitle,reviewDescription,productName,reviewerEmail,reviewedOn){
    
    this.router.navigateByUrl("/searchreview/"+reviewTitle+reviewDescription+productName+reviewerEmail+reviewedOn);
  }

}
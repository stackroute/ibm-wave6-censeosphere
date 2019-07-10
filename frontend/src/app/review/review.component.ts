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
  products: [];

  constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService) { 
    this.reviews=[];
  }

  ngOnInit() {
    this.landingservice.getAllProducts().subscribe((data:any)=>{
      console.log(data);
      this.products=data;
    })

    this.reviewService.getAllReviews().subscribe((data:any) => {
      console.log(data);
      this.reviews=data;
    })

  }
  
  onSubmit(){
    this.router.navigateByUrl("/searchreview");
  }

}
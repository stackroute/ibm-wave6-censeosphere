import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  reviews: any;

  constructor(private reviewService:ReviewService,private router:Router) { 
    this.reviews=[];
  }

  ngOnInit() {

    this.reviewService.getAllReviews().subscribe((data:any) => {
      console.log(data);
      this.reviews=data;
    })

  }
  
  onSubmit(){
    this.router.navigateByUrl("/searchreview");
  }

}
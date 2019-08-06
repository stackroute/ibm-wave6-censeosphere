import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card2',
  templateUrl: './card2.component.html',
  styleUrls: ['./card2.component.css']
})
export class Card2Component implements OnInit {

  constructor(private router: Router, private reviewService: ReviewService) { }
  product: any;
  reviewsgiven: any;
  ngOnInit() {
    this.product = JSON.parse(sessionStorage.getItem('data123'));
    console.log("in card component" + this.product);


    this.reviewService.getAllReviewsbyName(this.product.productName).subscribe((data: any) => {
      console.log(JSON.stringify(data));
      this.reviewsgiven = data.length;
      console.log("length of product list", this.reviewsgiven);
    });

  }

}



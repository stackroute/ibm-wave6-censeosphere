import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReviewService } from '../review.service';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private router: Router, private reviewService: ReviewService,private config: NgbRatingConfig) { 
    config.max = 5;
      config.readonly = true;
  }

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

  imageclick(product) {
    let a = JSON.stringify(product)
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    console.log("product info in card : " + JSON.stringify(product));
    sessionStorage.setItem('data', a);
    this.router.navigateByUrl("/productreview");
  }
}
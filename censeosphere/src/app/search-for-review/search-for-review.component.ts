import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchForReviewService } from '../search-for-review.service';
import { ReviewerdetailsService } from '../reviewerdetails.service';
import { Reviewerone } from '../reviewerone';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import subs from '../../assets/json/subCategory.json';
import { HttpClient } from '@angular/common/http';
import { LandingpageService } from '../landingpage.service';
@Component({
  selector: 'app-search-for-review',
  templateUrl: './search-for-review.component.html',
  styleUrls: ['./search-for-review.component.css']
})
export class SearchForReviewComponent implements OnInit {
  reviews = [];
  productName = "";
  price = "";
  reviewedOn = "";
  constructor(private router: Router, private searchforreview: SearchForReviewService,
    private activatedRoute: ActivatedRoute, private reviewerdetail: ReviewerdetailsService,
     private reviewService: ReviewService,private config: NgbRatingConfig,
     private http:HttpClient,private landingpageservice:LandingpageService) { 
       config.max = 5;
      config.readonly = true;
  }
  revieweremail = "";
  reviewerinfo: any;
  productname = "";
  score: any;
  reviewerone = new Reviewerone();
  reviewdetails: any;
  reviewerinfo1 = [];
  products: [];
  emailId: any;
  subs:any=subs;
  array=[];
  ngOnInit() {
    this.http.get('./assets/json/subCategory.json').subscribe((data:any) => {
            console.log(data, "Is this comming ???");
            this.array = data;
      
          })
           this.landingpageservice.getAllSubCategories().subscribe((data: any) => {
            console.log("inside get all ts file"+data);
            this.array= data;
            sessionStorage.setItem('sdata',data);
          })
    this.revieweremail = sessionStorage.getItem('reviewerEmail');
    console.log("email in write a review" + this.revieweremail);
    this.productname = JSON.parse(sessionStorage.getItem('data')).productName;
    console.log("productname in  write a review" + this.productname);
    this.reviewerdetail.getReviewer(this.revieweremail).subscribe((data: any) => {
      let a = JSON.stringify(data)
      console.log("reviewer data in search component" + JSON.stringify(data));
      sessionStorage.setItem('rdata', a);
      this.reviewerinfo = data;
      console.log("reviewer data in search component" + JSON.stringify(this.reviewerinfo));
    })
    this.reviewService.getAllReviewsbyName(this.productname).subscribe((data: any) => {
      console.log("review details in search" + JSON.stringify(data));
      this.reviewdetails = data;
      console.log(JSON.stringify(this.reviewdetails));
      for (let i = 0; i < data.length; i++) {
        let image = data[i].reviewerEmail;
        console.log("emailId", image);
        this.reviewerdetail.getReviewer(image).subscribe((data: any) => {
          console.log("reviewer data in search componentttttttttttttt" + JSON.stringify(data));
          this.reviewerinfo1.push(data);
          this.reviewdetails = this.reviewdetails.map((e, i) => {
            if (e.reviewerEmail === data.emailId) {
              e.image = data.image
            }
            console.log(e, "is this object has image ??")
            return e
          })
          console.log("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", this.reviewerinfo1);
        })
      }
    });
    this.products = JSON.parse(sessionStorage.getItem('data'));
  }
  yes(email) {
    console.log("inside yes " + email);
     this.reviewerdetail.getReviewer(email).subscribe((data: any) => {
      let a = JSON.stringify(data)
      console.log("reviewer data in yes method" + JSON.stringify(data));
      sessionStorage.setItem('rdata', a);
      this.reviewerone = data;
      console.log("reviewer data in yes method " + JSON.stringify(this.reviewerone));
      this.score = JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
      console.log("reviewer score" + this.score)
      this.score = this.score + 5;
      this.reviewerone.creditpoints = this.score;
      console.log("reviewer score after adding" + this.score)
      this.reviewerdetail.updateReviewer(this.reviewerone, email).subscribe((data: any) => {
        console.log("updated reviewer data in yes method" + JSON.stringify(data));
      })
    })
  }
  no(email) {
    console.log("inside yes" + email);
    this.reviewerdetail.getReviewer(email).subscribe((data: any) => {
      let a = JSON.stringify(data)
      console.log("reviewer data in no method" + JSON.stringify(data));
      sessionStorage.setItem('rdata', a);
      this.reviewerone = data;
      console.log("reviewer data in no method " + JSON.stringify(this.reviewerone));
      this.score = JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
      console.log("reviewer score" + this.score)
      this.score = this.score - 5;
      this.reviewerone.creditpoints = this.score;
      console.log("reviewer score after adding" + this.score)
      this.reviewerdetail.updateReviewer(this.reviewerone, email).subscribe((data: any) => {
        console.log("updated reviewer data in no method" + JSON.stringify(data));
      })
    })
  }
  wreview() {
    this.router.navigateByUrl("/writereview");
  }
  update() {
    this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword");
  }
  lpage() {
    this.router.navigateByUrl("/");
  }
  onClickSubCategory(subCategory){
        console.log("in landing page",subCategory);
       this.landingpageservice.findAllProductsBySubcategory(subCategory).
        subscribe(data=>{
          console.log("Product list : ",data);
           this.router.navigateByUrl("/productlist/"+subCategory);
        })
      }

      logoclick(){
        this.router.navigateByUrl("/");
       }
}
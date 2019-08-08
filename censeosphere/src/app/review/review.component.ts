import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router ,ActivatedRoute} from '@angular/router';
import{ LandingpageService }from '../landingpage.service';
import { Writereview } from '../writereview';
import { RecommendationService } from '../recommendation.service';
import { UpdateProfileService } from '../update-profile.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  reviews: any;
  products: [];
  writereview:Writereview=new Writereview();
  review:any;
  product : [];
 
  
  productDetails: any;
  // constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService,private activatedRoute:ActivatedRoute) { 
    // this.reviews=[];
   
  

  constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService,
    private activatedRoute:ActivatedRoute,private updates:UpdateProfileService,
    private route1:ActivatedRoute,
                 private recommendationService:RecommendationService,private config: NgbRatingConfig) {
                  config.max = 5;
                  config.readonly = true;            
    // this.reviews=[];
   }
  //  productDetails: [];
   productByFamily:[];

  // constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService) { 
  //   this.reviews=[];
  
 

  ngOnInit() {
    console.log(" Data on review page :",JSON.parse(sessionStorage.getItem('data')))
   
    
    this.landingservice.getAllProducts().subscribe((data:any)=>{
      console.log(data);
      this.productDetails=data;
    })

    this.reviewService.getAllReviews().subscribe((data:any) => {
      console.log(data);
      this.reviews=data;
    })

    this.product=JSON.parse(sessionStorage.getItem('data'));

    // this.reviewerDetails();

  }
  
  onSubmit(reviewTitle,reviewDesc){
    let date;
    date=sessionStorage.getItem('uploadedOn');

    this.writereview.reviewTitle=reviewTitle;
    this.writereview.reviewDescription=reviewDesc;
    this.writereview.productName=JSON.parse(sessionStorage.getItem('data')).productName;
    this.writereview.price=JSON.parse(sessionStorage.getItem('data')).price;
    // this.writereview.reviewedOn=JSON.parse(sessionStorage.getItem('data')).uploadedOn;
    this.writereview.reviewerEmail=sessionStorage.getItem('reviewerEmail');
    this.writereview.subCategory=JSON.parse(sessionStorage.getItem('data')).subCategory;

    this.writereview.creditpoints=JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
    let family=JSON.parse(sessionStorage.getItem('data')).productFamily;
    let subcategory=this.writereview.subCategory;
    this.reviewService.addReview(this.writereview).
      subscribe(data=>{
        console.log("data stored successfully");
      });
  
    this.router.navigateByUrl("/reviewerdash");
  } 
lpage()
{
  this.router.navigateByUrl("/")
}
update()
{
 this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword"); 
}

account()

{
  this.router.navigateByUrl("/")
}
logoclick(){
  this.router.navigateByUrl("/");
 }
}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { UpdateProfileService } from '../update-profile.service';
import { ProdownerserviceService } from '../prodownerservice.service';
import { ProductService } from '../product.service';
import { RecommendationService } from '../recommendation.service';
import { ReviewService } from '../review.service';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import subs from '../../assets/json/subCategory.json';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-reviwerdash',
  templateUrl: './reviwerdash.component.html',
  styleUrls: ['./reviwerdash.component.css']
})
export class ReviwerdashComponent implements OnInit {

  productDetails = [];
  productByFamily = [];
  products = [];
  productDetails1 = [];
  reviewsgiven = [];
  route: ActivatedRoute;

  subs:any=subs;
  array=[];

  constructor(private router: Router, private landingpageservice: LandingpageService, 
    private updates: UpdateProfileService,private route1: ActivatedRoute, 
    private prodownerservice: ProdownerserviceService, private productService: ProductService,
    private recommendationService: RecommendationService,private reviewService: ReviewService,
    private config: NgbRatingConfig,private http:HttpClient) {  config.max = 5;
      config.readonly = true;}

  ngOnInit() {
    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.productDetails=data;
    })
    this.reviewerDetails();
     

    this.http.get('./assets/json/subCategory.json').subscribe((data:any) => {
      console.log(data, "Is this comming ???");
      this.array = data;

    })
     this.landingpageservice.getAllSubCategories().subscribe((data: any) => {
      console.log("inside get all ts file"+data);
      this.array= data;
      sessionStorage.setItem('sdata',data);
    })

    this.reviewerDetails();

    this.recommendationService.getProductBySubCategory(sessionStorage.getItem('reviewerEmail')).
      subscribe((data: any) => {
        console.log("ppppppppppppppprrrrrrrrrrrrriiiiiiiiii",data);

    
        for (let i = 0; i < data.length; i++) {
          this.productService.getProduct(data[i].productName).
            subscribe((data: any) => {
              console.log("Products from recommendation by productSubCategory", data);

              this.productDetails.push(data);
            });

            // p
            this.reviewService.getAllReviewsbyName(data[i].productName).subscribe((data: any) => {
              this.reviewsgiven = data;
              console.log("length of product list", this.reviewsgiven.length);
        
        
              this.productDetails = this.productDetails.map((e, j) => {
                if (e.productName === data[0].productName) {
                  e.size = this.reviewsgiven.length;
        
                }
                console.log(e, "list size")
                return e
              })
        
            });
            // p

        }

        console.log("in product by family  : ", this.productDetails);
      });

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
  
  search(product) {
    console.log(product);
    this.productService.getProduct(product).subscribe(data => {
      let a = JSON.stringify(data)
      console.log("product info in rdashboard : ", JSON.stringify(data));
      sessionStorage.setItem('data', a);
      this.router.navigateByUrl("/rsearch");
    });
  }

  reviewerDetails() {
    const emailId = sessionStorage.getItem('reviewerEmail');
    console.log("Reviewer profile " + emailId);
    // sessionStorage.setItem("remailId",emailId);

    this.updates.getReviewerDetails(emailId).subscribe((data: any) => {
      console.log(data);
      sessionStorage.setItem("data1", JSON.stringify(data));
      console.log("inside reviewerdash" + data);
      // sessionStorage.setItem("data", JSON.stringify(data));

    });
  }

  imageclick(product) {
    let a = JSON.stringify(product)
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    console.log("product info from recommendation : " + JSON.stringify(product));
    sessionStorage.setItem('data', a);
    this.router.navigateByUrl("/rsearch");
  }

}

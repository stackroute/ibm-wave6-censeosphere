import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router , ActivatedRoute} from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { Authentication } from '../authentication';
import { LoginvalidationService } from '../loginvalidation.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Reviewer } from '../reviewer';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { error } from 'util';
import subs from 'src/assets/json/subCategory.json';
import { ReviewService } from '../review.service';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';


const helper = new JwtHelperService();
@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
 
})

export class LandingPageComponent implements OnInit, AfterViewInit {
  showFiller = false;
  productDetails = [];
  categories = [];
  subCategories = [];
  job = "";
  showComponent:any;
  reviewsgiven = [];
  productname = "";
  productname1="";
  // validatingForm: FormGroup;
  productDetails1 = [];
  reviewsgiven1=[];
  subs: any=subs;
  array=[];
  route: ActivatedRoute;

  form=new FormGroup({
    emailId: new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',Validators.required)
  })
  helper = new JwtHelperService();
  auth: Authentication = new Authentication();


  constructor(private http:HttpClient,private router: Router, private landingpageservice: LandingpageService,
     private loginvalidation: LoginvalidationService,private productService:ProductService,
    private reviewService: ReviewService,private config: NgbRatingConfig) {  config.max = 5;
      config.readonly = true; }
  ngAfterViewInit() {
    console.log(this.productDetails1.sort((e, f)=> parseFloat(e.rating) - parseFloat(f.rating)))
  }

  ngOnInit() {
    this.landingpageservice.getRecentProducts().subscribe((data: any) => {
      console.log(data)
       data.map((e, i) => {
        this.reviewService.getAllReviewsbyName(e.productName).subscribe((review: Array<{}>) => {
          e.size = review.length
          e.rating = parseFloat(e.rating).toFixed(1)
          this.productDetails.push(e)
          this.productDetails = this.productDetails.sort((e, f)=>  Date.parse(f.uploadedOn) - Date.parse(e.uploadedOn))
        });
      })
  })
  this.landingpageservice.getTrendingProducts().subscribe((data1: any) => {
    console.log(data1)
     data1.map((e, i) => {
      this.reviewService.getAllReviewsbyName(e.productName).subscribe((reviews: Array<{}>) => {
        e.size = reviews.length
        e.rating = parseFloat(e.rating).toFixed(1)
        this.productDetails1.push(e)
        this.productDetails1 = this.productDetails1.sort((e, f)=>  parseFloat(f.rating) - parseFloat(e.rating))
      });
    })
})

this.http.get('./assets/json/subCategory.json').subscribe((data:any) => {
  // console.log(data, "Is this comming ???");
  this.array = data;
})


    this.landingpageservice.getAllCategory().subscribe((data: any) => {
      console.log(data);
      this.categories = data;
    })
    this.landingpageservice.getAllSubCategories().subscribe((data: any) => {
      console.log(data);
      this.subCategories = data;
    })


  
  }

  onClick(role) {
    console.log(role);
    this.router.navigateByUrl("/account/" + role);
  }
  onClickPO(role1) {
    console.log(role1);
    this.router.navigateByUrl("/account/" + role1);
  }
  reviewer(emailId, password): any {

    this.auth.emailId = emailId;
    this.auth.password = password;
    this.loginvalidation.login(this.auth).
      subscribe((data: any) => {
        console.log("data from backend ", data.token);
        if (data.token) {
          console.log("in if");

          let role = this.helper.decodeToken(data.token).sub;
          console.log("we are having this......", data.token);

          console.log("in if print email   " + emailId);
          console.log("in if print password   " + password);
          console.log("in if print role   ", role);

          sessionStorage.setItem('reviewerEmail',emailId);
          
          if (role == this.job) {
            console.log(role);
            console.log("in if1");
            this.router.navigateByUrl("/reviewerdash");

          }
         
        }

      })
  }
  productOwner(emailId, password): any {
    this.auth.emailId = emailId;
    this.auth.password = password;
    this.loginvalidation.login(this.auth).
      subscribe((data: any) => {
        console.log("data from backend ", data.token);
        if (data.token) {
          console.log("in if");

          let role = this.helper.decodeToken(data.token).sub;
          console.log("we are having this......", data.token);

          console.log("in if print email   " + emailId);
          console.log("in if print password   " + password);
          console.log("in if print role   ", role);

          sessionStorage.setItem('productOwnerEmail',emailId);
          if (role == this.job) {
            console.log(role);
            console.log("in if1");
            console.log(emailId);
            this.router.navigateByUrl("/productownerdashboard/"+ emailId);

          }
          
        }

      })


  }

  onclick(rrole) {
    console.log(rrole);
    this.job = rrole;
  }
  onclick1(prole) {
    console.log(prole);
    this.job = prole;
  }
  

  newlogin(lemailId, lpassword): any {

    this.auth.emailId = lemailId;
    this.auth.password = lpassword;
    this.loginvalidation.login(this.auth).
      subscribe((data: any) => {
        console.log("data from backend ", data.token);
        if (data.token) {
          console.log("in if");

          let role = this.helper.decodeToken(data.token).sub;
          console.log("we are having this......", data.token);

          console.log("in if print email   " + lemailId);
          console.log("in if print password   " + lpassword);
          console.log("in if print role   ", role);

          if (role == 'reviewer') {
            console.log(role);
            console.log("in if1");

            sessionStorage.setItem('reviewerEmail', lemailId);
            this.router.navigateByUrl("/reviewerdash");
          }
          else if (role == 'product-owner') {
            sessionStorage.setItem('productOwnerEmail', lemailId);
            this.router.navigateByUrl("/productownerdashboard");
          }
        }
      });

  }

  
  onClickSubCategory(subCategory){
    // console.log("in landing page",subCategory);
   this.landingpageservice.findAllProductsBySubcategory(subCategory).
    subscribe(data=>{
      // console.log("Product list : ",data);
       this.router.navigateByUrl("/productlistguest/"+subCategory);
    })
  }
  showComponent1: any;
  searchproductL(product) {
    // console.log(product);
    this.productService.getProduct(product).
      subscribe(data => {
        // console.log("product info : ", data);
        let a = JSON.stringify(data)
        sessionStorage.setItem('data123', a);
        this.showComponent = true;
      },
        error => {
          this.showComponent1 = true;
        });
  }
  imageclick(product) {
    let a = JSON.stringify(product)
    // console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    // console.log("product info in card : " + JSON.stringify(product));
    sessionStorage.setItem('data', a);
    this.router.navigateByUrl("/productreview");
  } 
  logoclick(){
    this.router.navigateByUrl("/");
  }
}




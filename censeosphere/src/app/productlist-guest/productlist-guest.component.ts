import { Component, OnInit } from '@angular/core';
import pro from  'src/assets/json/data1.json';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginvalidationService } from '../loginvalidation.service';
import { ProductService } from '../product.service';
import { HttpClient } from '@angular/common/http';
import { LandingpageService } from '../landingpage.service';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Authentication } from '../authentication';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-productlist-guest',
  templateUrl: './productlist-guest.component.html',
  styleUrls: ['./productlist-guest.component.css']
})
export class ProductlistGuestComponent implements OnInit {

  listofproducts :any;
  job = "";
  array = [];
  subCategory=String;

  pro:any=pro;
  form=new FormGroup({
    emailId: new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',Validators.required)
  })
  helper = new JwtHelperService();
  auth: Authentication = new Authentication();
  constructor(private router: Router, private landingpageservice: LandingpageService,
     private loginvalidation: LoginvalidationService,private productService:ProductService,
     private http:HttpClient,private activatedRoute:ActivatedRoute,private config: NgbRatingConfig) {
      config.max = 5;
      config.readonly = true;
      }

  ngOnInit() {


    this.http.get('./assets/json/subCategory.json').subscribe((data:any) => {
      console.log(data, "Is this comming ???");
      this.array = data;

    });
    this.activatedRoute.params.subscribe(params =>{
      // console.log(params);
      this.subCategory=params['subCategory'];
      console.log(" from productlit componenent "+this.subCategory);

       this.call(this.subCategory);
    });
  }
  call(value)
  {
     
    console.log(value);
    this.landingpageservice.findAllProductsBySubcategory(value).
    subscribe(data=>{
      this.listofproducts=data;
      console.log("inside  method call",this.listofproducts)
     
    });
  }

  onClickSubCategory(subCategory){
    console.log(subCategory);
    this.subCategory=subCategory;
    this.landingpageservice.findAllProductsBySubcategory(subCategory).
    subscribe(data=>{
      this.listofproducts=data;
      console.log("inside  method onclick"+this.listofproducts)

     
  });

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
          this.router.navigateByUrl("/writereview/"+ emailId);

        }
        else {
          alert("provide valid credentailds");
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

          this.router.navigateByUrl("/writereview");
        }
        else if (role == 'product-owner') {
          this.router.navigateByUrl("/productownerdashboard");
        }
      }
    },
      error => {
        alert("Invalid credential");
      });

}


imageclickguest(pro){
  let a = JSON.stringify(pro);
    sessionStorage.setItem('data', a);
    this.router.navigateByUrl("/productreview");
 }
 logoclick(){
  this.router.navigateByUrl("/");
 }

}

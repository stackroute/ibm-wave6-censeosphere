import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { Authentication } from '../authentication';
import { LoginvalidationService } from '../loginvalidation.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Reviewer } from '../reviewer';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { error } from 'util';

const helper = new JwtHelperService();
@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})

export class LandingPageComponent implements OnInit {
  showFiller = false;
  productDetails = [];
  categories = [];
  subCategories = [];
  job = "";
  // validatingForm: FormGroup;


  form=new FormGroup({
    emailId: new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',Validators.required)
  })
  helper = new JwtHelperService();
  auth: Authentication = new Authentication();
  constructor(private router: Router, private landingpageservice: LandingpageService, private loginvalidation: LoginvalidationService,private productService:ProductService) { }

  ngOnInit() {

    // get modalFormDarkEmail() {
    //   return this.validatingForm.get('modalFormDarkEmail');
    // }
  
    // get modalFormDarkPassword() {
    //   return this.validatingForm.get('modalFormDarkPassword');
    // }


    this.landingpageservice.getAllProducts().subscribe((data: any) => {
      console.log(data);
      this.productDetails = data;
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
            this.router.navigateByUrl("/reviewerdash/"+ emailId);

          }
          else {
            alert("provide valid credentailds");
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

            this.router.navigateByUrl("/rdashboard");
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

  searchproduct(product){
     console.log(product);
      this.productService.getProduct(product).
      subscribe(data=>{
          console.log("product info : ",data);
          // this.router.navigateByUrl("/searchreview/"+data);
      });
     
     
  }
}




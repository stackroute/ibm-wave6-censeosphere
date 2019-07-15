import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
// import productList from 'src/assets/jsonfiles/product.json';
import {Authentication }  from '../authentication';
import {LoginvalidationService}   from '../loginvalidation.service';
import  { JwtHelperService } from '@auth0/angular-jwt';
import { Reviewer } from '../reviewer';
const helper = new JwtHelperService();
@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})

export class LandingPageComponent implements OnInit {
  // products1 : any[]=productList; 
  showFiller = false;
  products = [];
  categories = [];
  subCategories = [];
  job="";
   
   helper = new JwtHelperService();
   auth:Authentication=new Authentication();
  constructor(private router:Router,private landingpageservice:LandingpageService,private  loginvalidation:LoginvalidationService) { }

    ngOnInit() {
    // console.log(productList);
    // (productss:any) => productList;

    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.products=data;
    })

    this.landingpageservice.getAllCategory().subscribe((data:any) =>{
      console.log(data);
      this.categories=data;
    })
    this.landingpageservice.getAllSubCategories().subscribe((data:any) =>{
      console.log(data);
      this.subCategories=data;
    })
  }

  onClick(role){
    console.log(role);
    this.router.navigateByUrl("/account/"+role);
  }
  onClickPO(role1){
    console.log(role1);
    this.router.navigateByUrl("/account/"+role1);
  }
  reviewer(emailId,password):any{

    this.auth.emailId=emailId;
    this.auth.password=password;
    this.loginvalidation.login(this.auth).
      subscribe((data:any) =>{
        console.log("data from backend ",  data.token);  
        if (data.token) {
          console.log("in if");
    
         let role =  this.helper.decodeToken(data.token).sub;
         console.log("we are having this......",data.token);
    
         console.log("in if print email   "+ emailId);
         console.log("in if print password   "+ password);
         console.log("in if print role   ", role);
    
         if (role == this.job) {
           console.log(role);
          console.log("in if1");
          this.router.navigateByUrl("/reviwerdashboard");
          
         }
        else{
          alert("provide valid credentailds");
        }
        }
        
        })
      
      

    // this.router.navigateByUrl("/reviwerdashboard");
  }
  productOwner(emailId,password):any{
    this.auth.emailId=emailId;
    this.auth.password=password;
    this.loginvalidation.login(this.auth).
      subscribe((data:any) =>{
        console.log("data from backend ",  data.token);  
        if (data.token) {
          console.log("in if");
    
         let role =  this.helper.decodeToken(data.token).sub;
         console.log("we are having this......",data.token);
    
         console.log("in if print email   "+ emailId);
         console.log("in if print password   "+ password);
         console.log("in if print role   ", role);
    
         if (role == this.job) {
           console.log(role);
          console.log("in if1");
          this.router.navigateByUrl("/productownerdashboard");
          
         }
        else{
          alert("provide valid credentailds");
        }
        }
        
        })
    
    
  }

  onclick(rrole)
  {
    console.log(rrole);
    this.job=rrole;
  }
  onclick1(prole)
  {
    console.log(prole);
    this.job=prole;
  }

  newlogin(lemailId,lpassword):any{

    this.auth.emailId=lemailId;
    this.auth.password=lpassword;
    this.loginvalidation.login(this.auth).
      subscribe((data:any) =>{
        console.log("data from backend ",  data.token);  
        if (data.token) {
          console.log("in if");
    
         let role =  this.helper.decodeToken(data.token).sub;
         console.log("we are having this......",data.token);
    
         console.log("in if print email   "+ lemailId);
         console.log("in if print password   "+ lpassword);
         console.log("in if print role   ", role);
    
         if (role == 'reviewer') {
           console.log(role);
          console.log("in if1");
         
          this.router.navigateByUrl("/reviwerdashboard");
         }
        else if(role == 'product-owner'){
          this.router.navigateByUrl("/productownerdashboard");
        }
        }
         },
         error =>{
           alert("Invalid credential");
         });
         


  }

}




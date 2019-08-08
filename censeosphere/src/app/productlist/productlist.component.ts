import { Component, OnInit } from '@angular/core';
import pro from  'src/assets/json/data1.json';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginvalidationService } from '../loginvalidation.service';
import { ProductService } from '../product.service';
import { HttpClient } from '@angular/common/http';
import { LandingpageService } from '../landingpage.service';
@Component({
  selector: 'app-productlist',
  templateUrl: './productlist.component.html',
  styleUrls: ['./productlist.component.css']
})
export class ProductlistComponent implements OnInit {
  listofproducts :any;

  array = [];
  subCategory=String;

  pro:any=pro;
  
  
  constructor(private router: Router, private loginvalidation: LoginvalidationService,
    private productService:ProductService,private http:HttpClient,private activatedRoute:ActivatedRoute,
    private landingpageService:LandingpageService) { }

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
    // this.http.get('./assets/json/data1.json').subscribe((data:any) => {
    // console.log(data, "Is list there ???");
    // this.listofproducts = data;

    // })
  }
  call(value)
  {
     
    console.log(value);
    this.landingpageService.findAllProductsBySubcategory(value).
    subscribe(data=>{
      this.listofproducts=data;
      console.log("inside  method call"+this.listofproducts)
     
    });
     
  }
  // openSignin(){
  //   console.log("Sign in opened")
  //   this.landingpageService.
  // }
  onClickSubCategory(subCategory){
    console.log(subCategory);
    this.subCategory=subCategory;
    this.landingpageService.findAllProductsBySubcategory(subCategory).
    subscribe(data=>{
      this.listofproducts=data;
      console.log("inside  method onclick"+this.listofproducts);

     
  });

}

update()
{
 this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword"); 
}

lpage()
{
 this.router.navigateByUrl("/"); 
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
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
// import productList from 'src/assets/jsonfiles/product.json';

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
  constructor(private router:Router,private landingpageservice:LandingpageService) { }

  

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
  reviewer(){
    this.router.navigateByUrl("/reviwerdashboard");
  }
  productOwner(){
    this.router.navigateByUrl("/productownerdashboard");
  }

}




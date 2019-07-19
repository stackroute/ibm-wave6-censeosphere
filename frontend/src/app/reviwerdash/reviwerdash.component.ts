import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { ProductService } from '../product.service';
@Component({
  selector: 'app-reviwerdash',
  templateUrl: './reviwerdash.component.html',
  styleUrls: ['./reviwerdash.component.css']
})
export class ReviwerdashComponent implements OnInit {
  products = [];
  constructor(private router:Router,private landingpageservice:LandingpageService,private productService:ProductService) { }

  ngOnInit() {
    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.products=data;
    })
  }
  update()
  {
   this.router.navigateByUrl("/rprofile"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/"); 
  }
  
  search(product)
  {
    console.log(product);
      this.productService.getProduct(product).
      subscribe(data=>{
        let a = JSON.stringify(data)
          console.log("product info in rdashboard : ",JSON.stringify(data));
          sessionStorage.setItem('data', a);
          this.router.navigateByUrl("/rsearch"); 
      });
  } 
  
}

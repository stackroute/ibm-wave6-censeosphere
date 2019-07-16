import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
@Component({
  selector: 'app-reviewerdashboard',
  templateUrl: './reviewerdashboard.component.html',
  styleUrls: ['./reviewerdashboard.component.css']
})
export class ReviewerdashboardComponent implements OnInit {
  products = [];
  constructor(private router:Router,private landingpageservice:LandingpageService) { }

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
  onClick(){
    this.router.navigateByUrl("/reviwerdashboard"); 
  }

}

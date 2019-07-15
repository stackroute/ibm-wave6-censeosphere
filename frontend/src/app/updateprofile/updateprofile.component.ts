import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { SearchForReviewService } from '../search-for-review.service';


@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateprofileComponent implements OnInit {

  products=[];
  reviews=[];
  constructor(private router:Router,private landingpageservice:LandingpageService,private searchforreview:SearchForReviewService) { }

  ngOnInit() {

    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.products=data;
    })

    this.searchforreview.getAllReviews().subscribe((data:any) =>{
      console.log(data);
      this.reviews=data;
    })
  }
  update()
  {
   this.router.navigateByUrl("/rprofile"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/returnlanding"); 
  }
  
}

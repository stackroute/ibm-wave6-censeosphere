import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { UpdateProfileService } from '../update-profile.service';
import { ProdownerserviceService } from '../prodownerservice.service';
@Component({
  selector: 'app-reviwerdash',
  templateUrl: './reviwerdash.component.html',
  styleUrls: ['./reviwerdash.component.css']
})
export class ReviwerdashComponent implements OnInit {
  products = [];
  constructor(private router:Router,private landingpageservice:LandingpageService,private updates:UpdateProfileService,
    private route1:ActivatedRoute,private prodownerservice:ProdownerserviceService) { }

  ngOnInit() {
    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.products=data;
    })
    this.reviewerDetails();
  }
  update()
  {
   this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/"); 
  }
  search()
  {
    this.router.navigateByUrl("/rsearch"); 
  } 

  reviewerDetails(){
    const emailId=this.route1.snapshot.paramMap.get('emailId');
    console.log("Reviewer profile " +emailId);
    sessionStorage.setItem("pemailId",emailId);
  
    this.updates.getReviewerDetails(emailId).subscribe((data: any) => {
      console.log(data);
      sessionStorage.setItem("data", JSON.stringify(data));
     
    });
   }
  
}

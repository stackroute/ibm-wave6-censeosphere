import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProdownerserviceService } from '../prodownerservice.service';
import { LandingpageService } from '../landingpage.service';
import {Reviewer} from '../reviewer'
import { UpdateProfileService } from '../update-profile.service';
//import { getMaxListeners } from 'cluster';


@Component({
  selector: 'app-productownerdashboard',
  templateUrl: './productownerdashboard.component.html',
  styleUrls: ['./productownerdashboard.component.css']
})
export class ProductownerdashboardComponent implements OnInit {
  // productOwners: any;
  productOwners=[];

  products=[];
  products1=[];
  productDetails=[];


  reviewer;
  constructor(private updates:UpdateProfileService,private route1:ActivatedRoute,
    private router:Router,private prodownerservice:ProdownerserviceService,private landingpageservice:LandingpageService) { }
 


  ngOnInit() {
    this.prodownerservice.getAllProductOwners().subscribe((data:any) => {
      console.log(data);
      this.productOwners=data;
    })
    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.productDetails=data;
    })
   this.productOwnerDetails();
  }
  
  add()
  {
    this.router.navigateByUrl("/add-product");
  }
  update()
  {
    // const name=this.reviewer.name;
    //const emailId=this.reviewer.emailId;
    // const reconfirmPassword=this.reviewer.reconfirmPassword;
// console.log("emailId" +emailId);
   this.router.navigateByUrl('/prodownerupdateprofile/name/gmail/reconfirmpassword');
    // this.router.navigateByUrl("/prodownerupdateprofile");
  }

  lpage()
  {
    this.router.navigateByUrl("/");
  }

  productOwnerDetails(){
    const emailId=this.route1.snapshot.paramMap.get('emailId');
    console.log("product Owner profile " +emailId);
    sessionStorage.setItem("pemailId",emailId);
  
    this.updates.getProductOwnerDetails(emailId).subscribe((data: any) => {
      console.log(data);
      sessionStorage.setItem("data", JSON.stringify(data));
      //  this.products1=data;
      //  console.log("from productowner"+this.products1);
      // this.reviewer.name = data.name;
      // this.reviewer.emailId=data.emailId;
      // this.reviewer.reconfirmPassword=data.reconfirmPassword;
   
    });
   }


}

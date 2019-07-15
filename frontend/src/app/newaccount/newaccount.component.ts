import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Reviewer } from '../reviewer';
import { ProfileService } from '../profile.service';
@Component({
  selector: 'app-newaccount',
  templateUrl: './newaccount.component.html',
  styleUrls: ['./newaccount.component.css']
})
export class NewaccountComponent implements OnInit {
  public role= "";
  reviewer:Reviewer=new Reviewer();
  constructor(private router:Router,private activatedRoute:ActivatedRoute,private profileService:ProfileService) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params =>{
      // console.log(params);
      this.role=params['role'];
      console.log(" from newaccount componenent "+this.role);
     
    })
  }

  onClickS(name,emailId,rpassword):any
  {
    this.reviewer.emailId=emailId;
    this.reviewer.name=name;
    this.reviewer.reconfirmPassword=rpassword;
    this.reviewer.role=this.role;
    if(this.role == 'reviewer'){
    this.profileService.saveReviewer(this.reviewer).
    subscribe(
      data => {
        console.log("POST Request is successful ", data);},
        error => {
        alert("Invalid")
        console.log("Error", error);} 

);
        }
        else if(this.role == 'product-owner'){
          this.profileService.saveProductowner(this.reviewer).
    subscribe(
      data => {
        console.log("POST Request is successful ", data);},
        error => {
        alert("Invalid")
        console.log("Error", error);} 

);

    console.log("Reviewer "+this.reviewer);
    this.router.navigateByUrl("/");
        }

    console.log("Reviewer "+this.reviewer);
    this.router.navigateByUrl("/");
    
  }
  onClickC()
  {
    this.router.navigateByUrl("/");
  }
}

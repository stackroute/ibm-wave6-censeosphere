import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Reviewer } from '../reviewer';
import { ProfileService } from '../profile.service';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-newaccount',
  templateUrl: './newaccount.component.html',
  styleUrls: ['./newaccount.component.css']
})
export class NewaccountComponent implements OnInit {
  public role= "";
  reviewer:Reviewer=new Reviewer();
  profileFormGroup:FormGroup;
  verifyFormGroup:FormGroup;
  hide=true;
  validation_messages = {
    'name': [
      { type: 'required', message: 'Username is required' },
      { type: 'maxlength', message: 'Username cannot be more than 25 characters long' },
      { type: 'pattern', message: 'Your username must contain only numbers and letters' },
      { type: 'validUsername', message: 'Your username has already been taken' }
    ],
    'emailId': [
      { type: 'required', message: 'Email is required' },
      { type: 'pattern', message: 'Enter a valid email' }
    ],
    'reconfirmPassword': [
      { type: 'required', message: 'Confirm password is required' },
      { type: 'areEqual', message: 'Password mismatch' }
    ],
    'password': [
      { type: 'required', message: 'Password is required' },
      { type: 'minlength', message: 'Password must be at least 5 characters long' },
      { type: 'pattern', message: 'Your password must contain at least one uppercase, one lowercase, and one number' }
    ]
    }




  constructor(private router:Router,private activatedRoute:ActivatedRoute,private profileService:ProfileService,private fb:FormBuilder) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params =>{
      // console.log(params);
      this.role=params['role'];
      console.log(" from newaccount componenent "+this.role);
     
    });

    this.profileFormGroup=this.fb.group({
      name:['',Validators.compose([Validators.required,Validators.maxLength(20)])],
      emailId:['',Validators.compose([Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
    ])]
    });

    this.verifyFormGroup=this.fb.group({
      password:['',Validators.compose([
        Validators.minLength(5),
        Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')])],
      reconfirmPassword:['',Validators.required]
    });
  }

  onClickS(name,emailId,reconfirmPassword):any
  {
    this.reviewer.emailId=emailId;
    this.reviewer.name=name;
    this.reviewer.reconfirmPassword=reconfirmPassword;
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

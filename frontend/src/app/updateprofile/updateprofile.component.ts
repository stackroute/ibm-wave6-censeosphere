import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { SearchForReviewService } from '../search-for-review.service';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import {FormBuilder, Validators, FormControl, NgForm, AbstractControl} from '@angular/forms';
import { UpdateProfile} from '../update-profile'
import { UpdateProfileService } from '../update-profile.service';
import { ProductService } from '../product.service';


@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateprofileComponent implements OnInit {

   firstFormGroup:FormGroup;
   reviewer:any;
   productname="";
   email="";
  
  update1=new UpdateProfile();
  hide:true;

  productDetails:any;

  reviews=[];
  constructor(private router:Router,private landingpageservice:LandingpageService,
    private searchforreview:SearchForReviewService,private http:HttpClient,
    private _formBuilder: FormBuilder,private updates:UpdateProfileService,private productservice:ProductService) { }

  ngOnInit() {

    // this.landingpageservice.getAllProducts().subscribe((data:any) => {
    //   console.log(data);
    //   this.productDetails=data;
    // })

    // this.searchforreview.getAllReviews().subscribe((data:any) =>{
    //   console.log(data);
    //   this.reviews=data;
    // })

    this.firstFormGroup = this._formBuilder.group({
      imageCtrl: ['', Validators.required],
      NameCtrl: ['', Validators.required],
      passwordCtrl:['',[Validators.required,Validators.minLength(5)]],
      ReConfirmPasswordCtrl: ['', Validators.required],
      emailCtrl: ['', Validators.required],
      
   });


  //  this.reviewer=JSON.parse(sessionStorage.getItem("data"));
  //  console.log("inside update profile page" +this.reviewer);
   this.reviewer=JSON.parse(sessionStorage.getItem("data1"));
   console.log("Reviewer info. :"+this.reviewer);
   console.log("reviewer info2",this.reviewer.revieweswritten);
   this.reviews=this.reviewer.revieweswritten;
   this.reviews.forEach((y:any) => { 
    console.log("review list:",y);
   }) 
   for(let i=0;i<(this.reviews).length;i++)
   {
      this.productname=this.reviews[i].productName;
     console.log(this.productname);
   }
   this.productservice.getProduct(this.productname).subscribe((data:any) => {
    console.log(data);
    this.productDetails=data;
  })

   
   
  }

  updateDetails()
  {

    this.email=sessionStorage.getItem('reviewerEmail');
    console.log("from session"+this.email);
    this.update1.emailId=this.firstFormGroup.controls.emailCtrl.value;
    this.update1.name=this.firstFormGroup.controls.NameCtrl.value;
    this.update1.image=this.mediaName;
    this.update1.reconfirmPassword=this.firstFormGroup.controls.ReConfirmPasswordCtrl.value;
    this.update1.creditpoints=JSON.parse(sessionStorage.getItem('data1')).creditpoints;
    console.log(this.update1.emailId);
    console.log(this.update1.name);
    console.log(this.update1.image);
    console.log(this.update1.reconfirmPassword);
    console.log("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp",this.update1.creditpoints);
  

    this.updates.updateReviewerDetails(this.update1,this.email).
      subscribe(data =>{
        console.log("successfully updated");
      });
  }



  update()
  {
   this.router.navigateByUrl("/rprofile"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/returnlanding"); 
  }
  close()
  {
    this.router.navigateByUrl("/reviewerdash"); 
  }

  currentFileUpload:File;
  selectedVideo:FileList;
  mediaName:any;

  selectVideo(event){
    this.selectedVideo=event.target.files;
  }
  uploadVideo(){
    
    this.currentFileUpload = this.selectedVideo.item(0)
    this.mediaName=this.currentFileUpload.name;
   }



  // account()
  // {
  //   this.router.navigateByUrl("./reviewerdashboard");
  // }
}

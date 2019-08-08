import { Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import {FormBuilder, Validators, FormControl, NgForm, AbstractControl} from '@angular/forms';
import { UpdateProfile} from '../update-profile'
import { UpdateProfileService } from '../update-profile.service'
import { error } from 'util';

@Component({
  selector: 'app-prodownerupdateprofile',
  templateUrl: './prodownerupdateprofile.component.html',
  styleUrls: ['./prodownerupdateprofile.component.css']
})
export class ProdownerupdateprofileComponent implements OnInit {

  firstFormGroup:FormGroup;
  productOwner:any;

  email="";
  update=new UpdateProfile();
  hide:true;

  constructor(private router:Router,private http:HttpClient,
    private _formBuilder: FormBuilder,private updates:UpdateProfileService,private route1:ActivatedRoute) { }

  ngOnInit() {

    // const name=this.route1.snapshot.paramMap.get('name');
   // const emailId=this.route1.snapshot.paramMap.get('emailId');
    // const reconfirmPassword=this.route1.snapshot.paramMap.get('reconfirmPassword');


    this.firstFormGroup = this._formBuilder.group({
    //  imageCtrl: ['', Validators.required],
      NameCtrl: ['', Validators.required],
      ReConfirmPasswordCtrl: ['', Validators.required],
      emailCtrl: ['', Validators.required],
   });

  //  console.log(JSON.parse(sessionStorage.getItem("data")))
  this.productOwner=JSON.parse(sessionStorage.getItem("pdata"));
  console.log(this.productOwner);
  }
   
  updateDetails()
  {
    this.email=sessionStorage.getItem("pemailId");
    console.log("from session"+this.email);
    this.update.emailId=this.firstFormGroup.controls.emailCtrl.value;
    this.update.name=this.firstFormGroup.controls.NameCtrl.value;
    this.update.image=this.mediaName;
    this.update.reconfirmPassword=this.firstFormGroup.controls.ReConfirmPasswordCtrl.value;
    console.log(this.update.emailId);
    console.log(this.update.name);
    console.log(this.update.image);
    console.log(this.update.reconfirmPassword);


    console.log(this.update);
    console.log(this.email);
    
   
    this.updates.updateProductOwnerDetails(this.update,this.email).subscribe(data=>{
      console.log("successfully updated");
    });
   
  }

  lpage()
  {
    this.router.navigateByUrl("/");
  }


  account()
  {
    this.router.navigateByUrl("/productownerdashboard");
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
   logoclick(){
    this.router.navigateByUrl("/");
   }

}

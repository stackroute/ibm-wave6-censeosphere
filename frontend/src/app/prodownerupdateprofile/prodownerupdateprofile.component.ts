import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
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




  email="";
  update=new UpdateProfile();
  hide:true;

  constructor(private router:Router,private http:HttpClient,
    private _formBuilder: FormBuilder,private updates:UpdateProfileService) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      fileName: ['', Validators.required],
      NameCtrl: ['', Validators.required],
      ReConfirmPasswordCtrl: ['', Validators.required],
      emailCtrl: ['', Validators.required],
   });
  }

  updateDetails()
  {
    this.email=this.firstFormGroup.controls.emailCtrl.value;
    this.update.name=this.firstFormGroup.controls.NameCtrl.value;
    this.update.image=this.firstFormGroup.controls.fileName.value;
    this.update.reconfirmPassword=this.firstFormGroup.controls.ReConfirmPasswordCtrl.value;
    console.log(this.update);
    this.updates.saveProduct(this.update,this.email).
      subscribe(data =>{
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
}

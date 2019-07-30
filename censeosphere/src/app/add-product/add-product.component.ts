import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import {FormBuilder, Validators, FormControl, NgForm, AbstractControl} from '@angular/forms';
import {Product} from '../product';
import { ProductService } from '../product.service';




@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  
firstFormGroup:FormGroup;

product=new Product();

hide:true;

  constructor(private router:Router,private http:HttpClient,private _formBuilder: FormBuilder,private productDetails:ProductService) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      CategoryCtrl: ['', Validators.required],
      SubCategoryCtrl: ['', Validators.required],
      ProductNameCtrl: ['', Validators.required],
      ProductFamilyCtrl: ['', Validators.required],
      ProductPriceCtrl: ['', Validators.required],
      ProductSpecificationsCtrl: ['', Validators.required],
      ProductDescriptionCtrl: ['', Validators.required],
      ProductImageCtrl: ['', Validators.required],
   });

  }

saveProduct()
{
  this.product.category=this.firstFormGroup.controls.CategoryCtrl.value;
  this.product.subCategory=this.firstFormGroup.controls.SubCategoryCtrl.value;
  this.product.productName=this.firstFormGroup.controls.ProductNameCtrl.value;
  this.product.productFamily=this.firstFormGroup.controls.ProductFamilyCtrl.value;
  this.product.price=this.firstFormGroup.controls.ProductPriceCtrl.value;
  this.product.specifications=this.firstFormGroup.controls.ProductSpecificationsCtrl.value;
  this.product.description=this.firstFormGroup.controls.ProductDescriptionCtrl.value;
  // this.product.image=this.firstFormGroup.controls.ProductImageCtrl.value;
  this.product.image=this.mediaName;
  this.product.addedby=sessionStorage.getItem('productOwnerEmail');
  console.log(this.product);    environment:

  return this.productDetails.saveProduct(this.product).subscribe(data =>
    {
      console.log(data);
    });

}
lpage()
 {
   this.router.navigateByUrl("/");
 }
update()
 {

  // this.router.navigateByUrl('/prodownerupdateprofile/name/gmail/reconfirmpassword');

   this.router.navigateByUrl("/prodownerupdateprofile/name/gmail/reconfirmpassword");
 }
  account()
  {
    this.router.navigateByUrl("/productownerdashboard");
  }


  //priyanka 
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

//priyanka
}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http'
import { FormGroup } from '@angular/forms';
import {FormBuilder, Validators, FormControl, NgForm, AbstractControl} from '@angular/forms';
import {Product} from '../product';
import { ProductService } from '../product.service';
import { Observable } from 'rxjs/internal/Observable';
import {map, startWith} from 'rxjs/operators';
import subs from 'src/assets/json/subCategory.json';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  
firstFormGroup:FormGroup;
controls = new FormControl();
options: string[]=['Air Conditioner','Mobile Phone','Camera','Headphone',
'Laptop','Refrigerator','Washing Machine','Speaker'];
filteredOptions: Observable<string[]>;

control = new FormControl();
 categorys: string[] = ['Electronic Devices', 'Education', 'Hospital', 'Banking','Business'];
 filteredCategorys: Observable<string[]>;


product=new Product();
array=[];
hide:true;



  constructor(private router:Router,private http:HttpClient,
    private _formBuilder: FormBuilder,private productDetails:ProductService,
    private route: ActivatedRoute) { }

    private _filter2(value: string): string[] {
      const filterValue = value.toLowerCase();
      return this.options.filter(option => option.toLowerCase().includes(filterValue));
     }
    private _filter1(value: string): string[] {
      const filterValue = value.toLowerCase();
      return this.categorys.filter(category => category.toLowerCase().includes(filterValue));
     }

  ngOnInit() {
    this.filteredOptions = this.controls.valueChanges.pipe(
      startWith(''),
      map(value => this._filter2(value))
    );

    this.filteredCategorys = this.control.valueChanges.pipe(
      startWith(''),
      map(value => this._filter1(value))
    );

   
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
  this.product.rating=0;
  this.product.addedby=sessionStorage.getItem('productOwnerEmail');
  console.log(this.product);    environment:
  
  return this.productDetails.saveProduct(this.product).subscribe(data =>
    {
      console.log(data);
      this.router.navigateByUrl("/productownerdashboard");
    });
    
}
lpage()
 {
   this.router.navigateByUrl("/");
 }
update()
 {

 
   this.router.navigateByUrl("/prodownerupdateprofile/name/gmail/reconfirmpassword");
 }
  account()
  {
    this.router.navigateByUrl("/productownerdashboard");
  }


  
  currentFileUpload:File;
  selectedPhoto:FileList;
  mediaName:any;

  selectPhoto(event){
    this.selectedPhoto=event.target.files;
  }
  uploadPhoto(){
    
    this.currentFileUpload = this.selectedPhoto.item(0)
    this.mediaName=this.currentFileUpload.name;
   }

  logoclick(){
    this.router.navigateByUrl("/");
   }
  
}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProdownerserviceService } from '../prodownerservice.service';
import { LandingpageService } from '../landingpage.service';
import {Reviewer} from '../reviewer'
import { UpdateProfileService } from '../update-profile.service';
import { ProductService } from '../product.service';
//import { getMaxListeners } from 'cluster';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';


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
  myproducts:any;
  listofproducts=[];
  showComponent:any;
  showComponent1:any;

  reviewer;
  constructor(private updates:UpdateProfileService,private route1:ActivatedRoute,
    private router:Router,private prodownerservice:ProdownerserviceService,
    private landingpageservice:LandingpageService,private productService:ProductService,private config: NgbRatingConfig) {
        config.max = 5;
        config.readonly = true;
      }
    

     
 


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
   
  //  this.myproducts.productsadded.forEach((y:any) => { 
  //   console.log(y);
  //  })
  //  console.log("new list"+this.myproducts.productsadded.size());
  //  for(let i=0;i< this.myproducts.productsadded.length();i++)
  //  {
  //  this.listofproducts.push(this.myproducts.productsadded[i]);
  //  }
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

    // const emailId=this.route1.snapshot.paramMap.get('emailId');
    const emailId=sessionStorage.getItem("productOwnerEmail");
    console.log("product Owner profile " +emailId);
    sessionStorage.setItem("pemailId",emailId);
  
      this.updates.getProductOwnerDetails(emailId).subscribe((data: any) => {
      console.log(data);
      // sessionStorage.setItem("pdata", JSON.stringify(data));
      console.log("inside update" + JSON.stringify(data));
           this.listofproducts= data.productsadded;   
           this.listofproducts.forEach((y:any) => { 
               console.log(y);
              })                     
   
   
    });
   }
   
  deleteProduct(product){
    console.log(product);
    
     this.productService.deleteProduct(product).
     subscribe((data)=>{
         console.log("product info : ",data);
     } );

    this.listofproducts = this.listofproducts.filter(e => {
      return e.productName !== product.productName
    })
    //  this.listofproducts.pop();
    }
    account()
    {
      this.router.navigateByUrl("/productownerdashboard");
    }

    searchproductO(product){
      
      this.productService.searchProductByProductOwner(sessionStorage.getItem('productOwnerEmail'),product).
      subscribe(data=>{
        if(data==null){
          this.showComponent1 = true;
        }
        else{
          console.log("product info  : ",data);
          let a = JSON.stringify(data)
          sessionStorage.setItem('data123', a);
          this.showComponent = true;
        }
      });

    }
    logoclick(){
      this.router.navigateByUrl("/");
     }

  }

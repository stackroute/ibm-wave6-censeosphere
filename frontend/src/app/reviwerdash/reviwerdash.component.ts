import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { UpdateProfileService } from '../update-profile.service';
import { ProdownerserviceService } from '../prodownerservice.service';
import { ProductService } from '../product.service';
import { RecommendationService } from '../recommendation.service';


@Component({
  selector: 'app-reviwerdash',
  templateUrl: './reviwerdash.component.html',
  styleUrls: ['./reviwerdash.component.css']
})
export class ReviwerdashComponent implements OnInit {

  productDetails = [];
  productByFamily = [];
  products = [];

 
  route: ActivatedRoute;

  // constructor(private router:Router,private landingpageservice:LandingpageService,private updates:UpdateProfileService,
  //   private route1:ActivatedRoute,private prodownerservice:ProdownerserviceService,private productService:ProductService) { }

  
   constructor(private router:Router,private landingpageservice:LandingpageService,private updates:UpdateProfileService,
    private route1:ActivatedRoute,private prodownerservice:ProdownerserviceService,private productService:ProductService,
    private recommendationService:RecommendationService) { }



  ngOnInit() {
    this.landingpageservice.getAllProducts().subscribe((data:any) => {
      console.log(data);
      this.productDetails=data;
    })
    this.reviewerDetails();

    this.route1.params.subscribe(params =>{
      console.log("data fron recommendation by productFamily : ",params);
      this.recommendationService.getProductByFamily(JSON.parse(sessionStorage.getItem('data')).productFamily).
      subscribe((data:any)=>{
        console.log(data);
        // this.productDetails=data;

        for (let i = 0; i < data.length; i ++) {
        this.productService.getProduct(data[i].productName).
          subscribe((data:any)=>{
            console.log("aaaaaaaaaaaaaaaaa",data);
            // this.productDetails=data;
            this.productDetails.push(data);
          });
        }

        console.log("in product by family  : ",this.productDetails);
      });
    });
  }

  update()
  {
   this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/"); 
  }
  
  searches()
  {
    this.router.navigate(['card'],{relativeTo:this.route});
  } 
  search(product)
  {
    console.log(product);
      this.productService.getProduct(product).subscribe(data=>{
        let a = JSON.stringify(data)
          console.log("product info in rdashboard : ",JSON.stringify(data));
          sessionStorage.setItem('data', a);
          this.router.navigateByUrl("/rsearch"); 
      });
  } 

  reviewerDetails(){
    const emailId=this.route1.snapshot.paramMap.get('emailId');
    console.log("Reviewer profile " +emailId);
    sessionStorage.setItem("pemailId",emailId);
  
    this.updates.getReviewerDetails(emailId).subscribe((data: any) => {
      console.log("inside reviewerdash"+data);
      sessionStorage.setItem("data", JSON.stringify(data));
     
    });
   }
  
    
  
}

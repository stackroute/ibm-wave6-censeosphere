import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchForReviewService } from '../search-for-review.service';
import { ReviewerdetailsService } from '../reviewerdetails.service';
import { Reviewerone } from '../reviewerone';
import { ReviewService } from '../review.service';

@Component({
  selector: 'app-search-for-review',
  templateUrl: './search-for-review.component.html',
  styleUrls: ['./search-for-review.component.css']
})
export class SearchForReviewComponent implements OnInit {
  reviews = [];

  // constructor(private router:Router, private searchforreview:SearchForReviewService,private activatedRoute:ActivatedRoute) { }
  productName="";
  price="";
  reviewedOn="";

  constructor(private router:Router, private searchforreview:SearchForReviewService,private activatedRoute:ActivatedRoute,private reviewerdetail:ReviewerdetailsService,private  reviewService:ReviewService) { }
  // productName="";
  // price="";
  // reviewedOn="";
  revieweremail="";
  reviewerinfo:any;
  productname="";
  score:any;
  reviewerone=new Reviewerone();
  reviewdetails:any;
  ngOnInit() {


    this.revieweremail=sessionStorage.getItem('reviewerEmail');
    console.log("email in write a review"+this.revieweremail);


    this.productname=JSON.parse(sessionStorage.getItem('data')).productName;
    console.log("productname in  write a review"+this.productname);

     this.reviewerdetail.getReviewer(this.revieweremail).subscribe((data:any)=>{
      let a = JSON.stringify(data)
      console.log("reviewer data in search component"+JSON.stringify(data));
      sessionStorage.setItem('rdata', a);
      this.reviewerinfo=data;
       console.log("reviewer data in search component"+JSON.stringify(this.reviewerinfo));
    })
    
  //  this.searchforreview.getAllReviews().subscribe((data:any) =>{
  //   console.log(data);
  //   this.reviews=data;
  // })

    this.reviewService.getAllReviewsbyName(this.productname).subscribe((data:any) => {
    console.log("review details in search"+JSON.stringify(data));
    this.reviewdetails=data;
    console.log(JSON.stringify(this.reviewdetails));
    
  //  this.searchforreview.getAllReviews().subscribe((data:any) =>{
  //   console.log(data);
  //   this.reviews=data;

  })
}
  // wreview()
  // {

  
  // ngOnInit() {
  //   this.searchforreview.getAllReviews().subscribe((data:any) =>{
  //     console.log(data);
  //     this.reviews=data;
  //   })
  // }

       



  yes(email)
  {

      console.log("inside yes"+email);
      this.reviewerdetail.getReviewer(email).subscribe((data:any)=>{
      let a = JSON.stringify(data)
      console.log("reviewer data in yes method"+JSON.stringify(data));
      sessionStorage.setItem('rdata', a);
      this.reviewerone=data;
       console.log("reviewer data in yes method "+JSON.stringify(this.reviewerone));
       this.score=JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
       console.log("reviewer score"+this.score)
       this.score=this.score+5;
       this.reviewerone.creditpoints=this.score;
       console.log("reviewer score after adding"+this.score)
       this.reviewerdetail.updateReviewer(this.reviewerone,email).subscribe((data:any)=>
       {

        console.log("updated reviewer data in yes method"+JSON.stringify(data));
       })



    })



  }

  no(email)
  {

    console.log("inside yes"+email);
    this.reviewerdetail.getReviewer(email).subscribe((data:any)=>{
    let a = JSON.stringify(data)
    console.log("reviewer data in no method"+JSON.stringify(data));
    sessionStorage.setItem('rdata', a);
    this.reviewerone=data;
     console.log("reviewer data in no method "+JSON.stringify(this.reviewerone));
     this.score=JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
     console.log("reviewer score"+this.score)
     this.score=this.score-5;
     this.reviewerone.creditpoints=this.score;
     console.log("reviewer score after adding"+this.score)
     this.reviewerdetail.updateReviewer(this.reviewerone,email).subscribe((data:any)=>
     {

      console.log("updated reviewer data in no method"+JSON.stringify(data));
     })



    })

    
  }

wreview()
    {
  
      this.router.navigateByUrl("/writereview");

  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchForReviewService } from '../search-for-review.service';


@Component({
  selector: 'app-search-for-review',
  templateUrl: './search-for-review.component.html',
  styleUrls: ['./search-for-review.component.css']
})
export class SearchForReviewComponent implements OnInit {
  reviews = [];
  constructor(private router:Router, private searchforreview:SearchForReviewService) { }

  ngOnInit() {

  this.searchforreview.getAllReviews().subscribe((data:any) =>{
    console.log(data);
    this.reviews=data;
  })
}
     wreview()
  {

    this.router.navigateByUrl("/writereview");
  }
}

  


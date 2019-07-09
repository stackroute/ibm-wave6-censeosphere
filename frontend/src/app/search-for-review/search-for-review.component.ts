import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-search-for-review',
  templateUrl: './search-for-review.component.html',
  styleUrls: ['./search-for-review.component.css']
})
export class SearchForReviewComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  wreview()
  {

    this.router.navigateByUrl("/writereview");
  }
}

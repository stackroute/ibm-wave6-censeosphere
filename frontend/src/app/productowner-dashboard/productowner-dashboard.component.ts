import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productowner-dashboard',
  templateUrl: './productowner-dashboard.component.html',
  styleUrls: ['./productowner-dashboard.component.css']
})
export class ProductownerDashboardComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  update()
  {
   this.router.navigateByUrl("/rprofile"); 
  }
  lpage()
  {
   this.router.navigateByUrl("/returnlanding"); 
  }
  
}

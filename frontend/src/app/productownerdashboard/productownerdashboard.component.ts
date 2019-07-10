import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-productownerdashboard',
  templateUrl: './productownerdashboard.component.html',
  styleUrls: ['./productownerdashboard.component.css']
})
export class ProductownerdashboardComponent implements OnInit {

  constructor(private router:Router) { }


  ngOnInit() {
  }
  
  add()
  {
    this.router.navigateByUrl("/add-product");
  }
  lpage(){
    this.router.navigateByUrl("/");
  }

}

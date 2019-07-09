import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProdownerserviceService } from '../prodownerservice.service';

@Component({
  selector: 'app-productownerdashboard',
  templateUrl: './productownerdashboard.component.html',
  styleUrls: ['./productownerdashboard.component.css']
})
export class ProductownerdashboardComponent implements OnInit {
  productOwners: any;

  constructor(private router:Router,private prodownerservice:ProdownerserviceService) { }

  ngOnInit() {
    this.prodownerservice.getAllProductOwners().subscribe((data:any) => {
      console.log(data);
      // this.productOwners=data;
    })
  }

  update()
  {
    this.router.navigateByUrl("/prodownerupdateprofile");
  }

  lpage()
  {
    this.router.navigateByUrl("/");
  }
}

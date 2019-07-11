import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http'




@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  selectFile: File=null;

  constructor(private router:Router,private http:HttpClient) { }

  ngOnInit() {
  }


lpage()
 {
   this.router.navigateByUrl("/");
 }
update()
 {
   this.router.navigateByUrl("/prodownerupdateprofile");
 }
 account()
 {
   this.router.navigateByUrl("/productownerdashboard")
 }

 

}
  
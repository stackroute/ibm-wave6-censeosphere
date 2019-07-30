import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private router:Router) { }

  product:any;
  ngOnInit() {

    this.product=JSON.parse(sessionStorage.getItem('data123'));
    console.log("in card component"+this.product);
  }

  imageclick(product){
    let a = JSON.stringify(product)
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    console.log("product info in card : "+JSON.stringify(product));
    sessionStorage.setItem('data', a);
    this.router.navigateByUrl("/productreview"); 
   } 
}
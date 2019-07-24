import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }

  productdetails:any;
  ngOnInit() {

    this.productdetails=sessionStorage.getItem('data');
    console.log("in card component"+this.productdetails);
  }
}
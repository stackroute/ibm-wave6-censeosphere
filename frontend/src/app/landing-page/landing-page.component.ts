import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  showFiller = false;

  constructor(private router:Router) { }

  ngOnInit() {
  }

  onClick(){
    this.router.navigateByUrl("/account");
  }
  reViwer()
  {
    this.router.navigateByUrl("/reviwerdashboard");
  }
  

}




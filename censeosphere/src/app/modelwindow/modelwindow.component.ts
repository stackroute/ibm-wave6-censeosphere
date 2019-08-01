import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modelwindow',
  templateUrl: './modelwindow.component.html',
  styleUrls: ['./modelwindow.component.css']
})
export class ModelwindowComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  account()
  {
    this.router.navigateByUrl("/");
  }

}

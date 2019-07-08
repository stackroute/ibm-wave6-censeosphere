import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-newaccount',
  templateUrl: './newaccount.component.html',
  styleUrls: ['./newaccount.component.css']
})
export class NewaccountComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  onClickS()
  {
    this.router.navigateByUrl("/");
  }
  onClickC()
  {
    this.router.navigateByUrl("/");
  }
}

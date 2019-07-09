import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prodownerupdateprofile',
  templateUrl: './prodownerupdateprofile.component.html',
  styleUrls: ['./prodownerupdateprofile.component.css']
})
export class ProdownerupdateprofileComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  lpage()
  {
    this.router.navigateByUrl("/");
  }
}

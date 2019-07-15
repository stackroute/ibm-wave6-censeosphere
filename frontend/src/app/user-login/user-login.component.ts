import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { from } from 'rxjs';
import { UserService } from '../user.service';
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

constructor(private loginService:LoginService, private route: ActivatedRoute,private router:Router, private userService:UserService) { }
  arrayOfUser:any=[];


  ngOnInit() {

  }

  user:User=new User();

  login(username,password) : any {
    this.user.emailId=username;
    this.user.password=password;

    console.log(this.user.emailId);
    console.log(this.user.password);
    
    this.userService.login(this.user).
    subscribe(data =>{
      alert("Valid")
      console.log("successful",data);},
      error =>{
        alert("invalid Credentials")
        console.log("Error",error);
      }
    );
  }
}



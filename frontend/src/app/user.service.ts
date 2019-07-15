import { Injectable } from '@angular/core';

import { HttpClient,HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
UserService: any;
route: any;
headers= new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  constructor(private http:HttpClient) { }

  getAllUsers():any{
    return this.http.get("http://172.17.0.1:8088/api/user/getuser");
  }

  saveUser(user:User){
    console.log("sadgui"+user)
    return this.http.post<User>("http://172.17.0.1:8088/api/user/users",user);
  }

  login(user:User){
    return this.http.post<User>("http://172.17.0.1:8088/api/user/user",user);

  }
}

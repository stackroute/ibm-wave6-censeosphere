import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {Authentication} from './authentication';
@Injectable({
  providedIn: 'root'
})
export class LoginvalidationService {

loginvalidation: any;
route: any;
headers= new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  constructor(private http:HttpClient) { }


  login(auth:Authentication){
    console.log("in service "+JSON.stringify(auth));
    return this.http.post("http://13.126.244.58:8083/user-login-service/api/user/user",auth);

  }
}
   
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
    console.log("in service "+auth)
    return this.http.post("http://172.17.0.1:8088/api/user/user",auth);

  }
}
   
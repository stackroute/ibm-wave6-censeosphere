import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Reviewer } from './reviewer';
@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  profileService: any;
  route: any;
  httpOptions={
  headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}
  
  constructor(private http:HttpClient) { }

  saveReviewer(reviewer:Reviewer){
    console.log("hello there ", reviewer);
   return this.http.post<Reviewer>("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer",reviewer);
  }
  saveProductowner(reviewer:Reviewer)
  {
    console.log("helloooooooo "+reviewer);
    return this.http.post<Reviewer>("http://13.126.244.58:8083/product-owner-service/api/v1/product",reviewer);  
  }
}

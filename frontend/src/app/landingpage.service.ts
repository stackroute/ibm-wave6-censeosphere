import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LandingpageService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
      
    })
  };

  getAllProducts(){
    return this.http.get('http://localhost:3000/productDetails', this.httpOptions);
  }
  getAllCategory(){
    return this.http.get('http://localhost:3001/category', this.httpOptions);
  }
  getAllSubCategories(){
    return this.http.get('http://localhost:3001/subCategories', this.httpOptions);
  }
}

import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private reviewService:ReviewService,private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
      
    })
  };
  addReview(){
    return this.http.get('http://localhost:8089/review', this.httpOptions);
  }
  getAllReviews(){
    return this.http.get('http://localhost:8089/reviews', this.httpOptions);
  }
}

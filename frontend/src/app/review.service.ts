import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Writereview } from './writereview';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  httpOptions={
    headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}

  addReview(writereview:Writereview){
    console.log("data in review service :",writereview);
    return this.http.post<Writereview>('http://localhost:8089/api/v1/review',writereview);
  }
  getAllReviews(){
    return this.http.get('http://localhost:8089/reviews', this.httpOptions);
  }
}
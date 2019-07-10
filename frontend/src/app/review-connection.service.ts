import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ReviewComponent } from './review/review.component';

@Injectable({
  providedIn: 'root'
})
export class ReviewConnectionService {
route:any;
ReviewConnection:any;
headers= new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  constructor(private http:HttpClient) { }

  addReview(reviewConn:ReviewConnectionService){

    return this.http.post<ReviewComponent>('http://localhost:8089/review', reviewConn);
  }
  getAllReviews(){
    return this.http.get('http://localhost:8089/reviews');
  }
}

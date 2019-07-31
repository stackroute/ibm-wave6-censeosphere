import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Writereview } from './writereview';
// import { Reviewerone } from './reviewerone';
@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  httpOptions={
    headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}

  addReview(writereview:Writereview){
    console.log("data in review service :",writereview);
    return this.http.post<Writereview>('http://13.126.244.58:8083/review-service/api/v1/review',writereview);
  }
  getAllReviews(){
    return this.http.get('http://13.126.244.58:8083/review-service/api/v1/reviews', this.httpOptions);
  }

  getAllReviewsbyName(productname)
  {
    console.log(productname);
    return this.http.get('http://13.126.244.58:8083/review-service/api/v1/byname/'+productname);
  }
}
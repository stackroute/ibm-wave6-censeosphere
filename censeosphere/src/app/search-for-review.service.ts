import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders }from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class SearchForReviewService {

  constructor(private http: HttpClient) { }
  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json'
      
  //   })
  // };
  httpOptions={
    headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}

  // getAllReviews(){
  //   return this.http.get('http://localhost:7070/reviews', this.httpOptions);
  // }


}

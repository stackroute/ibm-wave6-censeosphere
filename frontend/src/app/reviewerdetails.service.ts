import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Reviewerone } from './reviewerone';

@Injectable({
  providedIn: 'root'
})
export class ReviewerdetailsService {


  httpOptions={
    headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}

  constructor(private http:HttpClient) { }


getReviewer(email)
{

  console.log("in Reviwerdetailservice ", email);
  return this.http.get<Reviewerone>("http://localhost:8087/api/v1/reviewer/" +email);
}

updateReviewer(reviewerone:Reviewerone,email)
  {
    console.log("data in reviewdetrail service in update :",reviewerone);
    return this.http.put<Reviewerone>("http://localhost:8087/api/v1/reviewer/"+email,reviewerone);

  }

}

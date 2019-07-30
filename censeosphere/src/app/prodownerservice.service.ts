import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProdownerserviceService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
      
    })
};
getAllProductOwners(){
  return this.http.get('http://localhost:4000/productOwners', this.httpOptions);
}
}

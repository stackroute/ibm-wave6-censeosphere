import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http: HttpClient) { }

  httpOptions={
    headers : new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}

  

    getProductBySubCategory(emailId:string){
      console.log("data in recommendation service :",emailId);
        return this.http.get<Product>('http://13.126.244.58:8083/recommendation-service/api/v1/recommendedproducts/'+emailId);
       
    }

    
}

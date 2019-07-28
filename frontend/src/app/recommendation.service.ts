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

    // getProductByFamily(productFamily:string){
    //     console.log("data in recommendation service :",productFamily);
    //     return this.http.get<Product>('http://localhost:8085/rest/neo4j/product/productfamily/'+productFamily);
      
    // }

    getProductBySubCategory(emailId:string){
      console.log("data in recommendation service :",emailId);
        return this.http.get<Product>('http://localhost:8085/rest/neo4j/product/recommendedproduct/'+emailId);
      
    }

    
}

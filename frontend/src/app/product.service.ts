import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Product} from './product'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  _url = 'http://localhost:8081/api/v1/product';

 httpOptions = {
   headers: new HttpHeaders({
     'Content-type': 'application/json',
     'Access-Control-Allow-Origin': '*'
   })
 }

  constructor(private _http: HttpClient) { }

  saveProduct(product:Product) {
    return this._http.post<Product>(this._url, product, this.httpOptions);
  }

  getProduct(productName){
    console.log("from service : "+productName);
    return this._http.get("http://localhost:8081/api/v1/product/"+productName, this.httpOptions);
  }

  deleteProduct(productName:String)
  {
    console.log("from service :"+productName);
    return this._http.delete<Product>("http://localhost:8081/api/v1/product/"+productName, this.httpOptions);
  }

}

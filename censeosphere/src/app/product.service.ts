import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from './product'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //_url = 'http://13.126.244.58:8083/product-search-service/api/v1/product';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  }

  constructor(private _http: HttpClient) { }

  saveProduct(product: Product) {
    console.log("value of product is" + product)
    return this._http.post<Product>("http://13.126.244.58:8083/product-search-service/api/v1/product", product, this.httpOptions);
  }

  getProduct(productName) {
    console.log("from service : " + productName);
    return this._http.get("http://13.126.244.58:8083/product-search-service/api/v1/product/" + productName, this.httpOptions);
  }

  deleteProduct(productName) {
    console.log("from service :" + productName);
    return this._http.delete<Product>("http://13.126.244.58:8083/product-search-service/api/v1/product/" + productName, this.httpOptions);
  }

  searchProductByProductOwner(emailId: string, product: String) {
    console.log("product owner email for search", emailId);
    console.log("Product name for search ", product);
    return this._http.get("http://13.126.244.58:8083/product-search-service/api/v1/search/" + emailId + "/" + product, this.httpOptions);
  }
}

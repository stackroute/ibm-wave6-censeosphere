import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LandingpageService {
  route:any;

  headers= new HttpHeaders({'Access-Control-Allow-Origin' : '*'})
  
  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'

    })
  };


  getRecentProducts(){
    return this.http.get('http://13.126.244.58:8083/product-search-service/api/v1/recentproducts', this.httpOptions);
  
  }

  getTrendingProducts()
  {
    return this.http.get('http://13.126.244.58:8083/product-search-service/api/v1/trendingproducts', this.httpOptions);
  }

  
  getAllProducts(){
    return this.http.get('http://localhost:3000/products', this.httpOptions);
  }


  getAllCategory(){
    return this.http.get('http://localhost:3001/category', this.httpOptions);
  }
  getAllSubCategories(){
    return this.http.get('http://localhost:3001/subCategories', this.httpOptions);
  }
  findAllProductsBySubcategory(searchConn:string)
  {
    console.log("in landing servie suncategory:",searchConn);
    return this.http.get('http://13.126.244.58:8083/search-service/api/v1/products/'+searchConn);
   
  } 
  
  getAllSubcategories()
  {
    console.log("inside getallsubcategories");
    return this.http.get('http://13.126.244.58:8083/search-service/api/v1/subcategories',this.httpOptions);
  }
}

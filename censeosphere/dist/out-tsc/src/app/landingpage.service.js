import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let LandingpageService = class LandingpageService {
    constructor(http) {
        this.http = http;
        this.headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' });
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
    }
    getRecentProducts() {
        return this.http.get('http://13.126.244.58:8083/product-search-service/api/v1/recentproducts', this.httpOptions);
    }
    getTrendingProducts() {
        return this.http.get('http://13.126.244.58:8083/product-search-service/api/v1/trendingproducts', this.httpOptions);
    }
    getAllProducts() {
        return this.http.get('http://localhost:3000/products', this.httpOptions);
    }
    getAllCategory() {
        return this.http.get('http://localhost:3001/category', this.httpOptions);
    }
    getAllSubCategories() {
        return this.http.get('http://localhost:3001/subCategories', this.httpOptions);
    }
    findAllProductsBySubcategory(searchConn) {
        console.log("in landing servie suncategory:", searchConn);
        return this.http.get('http://13.126.244.58:8083/search-service/api/v1/products/' + searchConn);
    }
    getAllSubcategories() {
        console.log("inside getallsubcategories");
        return this.http.get('http://13.126.244.58:8083/search-service/api/v1/subcategories', this.httpOptions);
    }
};
LandingpageService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], LandingpageService);
export { LandingpageService };
//# sourceMappingURL=landingpage.service.js.map
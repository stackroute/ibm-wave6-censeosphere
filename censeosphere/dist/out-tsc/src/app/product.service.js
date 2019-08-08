import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let ProductService = class ProductService {
    constructor(_http) {
        this._http = _http;
        //_url = 'http://13.126.244.58:8083/product-search-service/api/v1/product';
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            })
        };
    }
    saveProduct(product) {
        console.log("value of product is" + product);
        return this._http.post("http://13.126.244.58:8083/product-search-service/api/v1/product", product, this.httpOptions);
    }
    getProduct(productName) {
        console.log("from service : " + productName);
        return this._http.get("http://13.126.244.58:8083/product-search-service/api/v1/product/" + productName, this.httpOptions);
    }
    deleteProduct(productName) {
        console.log("from service :" + productName);
        return this._http.delete("http://13.126.244.58:8083/product-search-service/api/v1/product/" + productName, this.httpOptions);
    }
    searchProductByProductOwner(emailId, product) {
        console.log("product owner email for search", emailId);
        console.log("Product name for search ", product);
        return this._http.get("http://13.126.244.58:8083/product-search-service/api/v1/search/" + emailId + "/" + product, this.httpOptions);
    }
};
ProductService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ProductService);
export { ProductService };
//# sourceMappingURL=product.service.js.map
import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
let RecommendationService = class RecommendationService {
    constructor(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })
        };
    }
    getProductBySubCategory(emailId) {
        console.log("data in recommendation service :", emailId);
        return this.http.get('http://13.126.244.58:8083/recommendation-service/api/v1/recommendedproducts/' + emailId);
    }
};
RecommendationService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], RecommendationService);
export { RecommendationService };
//# sourceMappingURL=recommendation.service.js.map
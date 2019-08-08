import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let ProfileService = class ProfileService {
    constructor(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })
        };
    }
    saveReviewer(reviewer) {
        console.log("hello there ", reviewer);
        return this.http.post("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer", reviewer);
    }
    saveProductowner(reviewer) {
        console.log("helloooooooo " + reviewer);
        return this.http.post("http://13.126.244.58:8083/product-owner-service/api/v1/product", reviewer);
    }
};
ProfileService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ProfileService);
export { ProfileService };
//# sourceMappingURL=profile.service.js.map
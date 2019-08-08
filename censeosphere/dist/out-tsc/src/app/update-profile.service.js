import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let UpdateProfileService = class UpdateProfileService {
    constructor(_http) {
        this._http = _http;
        // _url = 'http://localhost:8081/api/v1/product/{emailId}';
        this.headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' });
    }
    updateProductOwnerDetails(update, emailId) {
        console.log("from service : ", emailId);
        return this._http.put("http://13.126.244.58:8083/product-owner-service/api/v1/products/" + emailId, update);
    }
    updateReviewerDetails(update1, emailId) {
        console.log("from service : ", emailId);
        return this._http.put("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer/" + emailId, update1);
    }
    getProductOwnerDetails(emailId) {
        console.log("from service : ", emailId);
        return this._http.get("http://13.126.244.58:8083/product-owner-service/api/v1/product/" + emailId);
    }
    getReviewerDetails(emailId) {
        console.log("from service : ", emailId);
        return this._http.get("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer/" + emailId);
    }
};
UpdateProfileService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], UpdateProfileService);
export { UpdateProfileService };
//# sourceMappingURL=update-profile.service.js.map
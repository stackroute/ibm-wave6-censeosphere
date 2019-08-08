import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let ReviewerdetailsService = class ReviewerdetailsService {
    constructor(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })
        };
    }
    getReviewer(email) {
        console.log("in Reviwerdetailservice ", email);
        return this.http.get("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer/" + email);
    }
    updateReviewer(reviewerone, email) {
        console.log("data in reviewdetrail service in update :", reviewerone);
        return this.http.put("http://13.126.244.58:8083/reviewer-profile-service/api/v1/reviewer/" + email, reviewerone);
    }
};
ReviewerdetailsService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ReviewerdetailsService);
export { ReviewerdetailsService };
//# sourceMappingURL=reviewerdetails.service.js.map
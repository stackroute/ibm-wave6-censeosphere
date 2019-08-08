import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
let ReviewConnectionService = class ReviewConnectionService {
    constructor(http) {
        this.http = http;
        this.headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' });
    }
    addReview(reviewConn) {
        return this.http.post('http://13.126.244.58:8083/review-service/review', reviewConn);
    }
    getAllReviews() {
        return this.http.get('http://13.126.244.58:8083/review-service/reviews');
    }
};
ReviewConnectionService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ReviewConnectionService);
export { ReviewConnectionService };
//# sourceMappingURL=review-connection.service.js.map
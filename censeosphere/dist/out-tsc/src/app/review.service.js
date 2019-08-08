import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
// import { Reviewerone } from './reviewerone';
let ReviewService = class ReviewService {
    constructor(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })
        };
    }
    addReview(writereview) {
        console.log("data in review service :", writereview);
        return this.http.post('http://13.126.244.58:8083/review-service/api/v1/review', writereview);
    }
    getAllReviews() {
        return this.http.get('http://13.126.244.58:8083/review-service/api/v1/reviews', this.httpOptions);
    }
    getAllReviewsbyName(productname) {
        console.log(productname);
        return this.http.get('http://13.126.244.58:8083/review-service/api/v1/byname/' + productname);
    }
};
ReviewService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ReviewService);
export { ReviewService };
//# sourceMappingURL=review.service.js.map
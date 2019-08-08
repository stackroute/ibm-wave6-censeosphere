import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let SearchForReviewService = class SearchForReviewService {
    constructor(http) {
        this.http = http;
        // httpOptions = {
        //   headers: new HttpHeaders({
        //     'Content-Type':  'application/json'
        //   })
        // };
        this.httpOptions = {
            headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*' })
        };
    }
};
SearchForReviewService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], SearchForReviewService);
export { SearchForReviewService };
//# sourceMappingURL=search-for-review.service.js.map
import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let LoginvalidationService = class LoginvalidationService {
    constructor(http) {
        this.http = http;
        this.headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' });
    }
    login(auth) {
        console.log("in service " + JSON.stringify(auth));
        return this.http.post("http://13.126.244.58:8083/user-login-service/api/user/user", auth);
    }
};
LoginvalidationService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], LoginvalidationService);
export { LoginvalidationService };
//# sourceMappingURL=loginvalidation.service.js.map
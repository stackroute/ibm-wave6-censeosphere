import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let UserService = class UserService {
    constructor(http) {
        this.http = http;
        this.headers = new HttpHeaders({ 'Access-Control-Allow-Origin': '*' });
    }
    getAllUsers() {
        return this.http.get("http://13.126.244.58:8083/user-login-service/api/user/getuser");
    }
    saveUser(user) {
        console.log("sadgui" + user);
        return this.http.post("http://13.126.244.58:8083/user-login-service/api/user/users", user);
    }
    login(user) {
        return this.http.post("http://13.126.244.58:8083/user-login-service/api/user/user", user);
    }
};
UserService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], UserService);
export { UserService };
//# sourceMappingURL=user.service.js.map
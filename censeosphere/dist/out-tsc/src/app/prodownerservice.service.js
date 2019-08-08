import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
let ProdownerserviceService = class ProdownerserviceService {
    constructor(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
    }
    getAllProductOwners() {
        return this.http.get('http://localhost:4000/productOwners', this.httpOptions);
    }
};
ProdownerserviceService = tslib_1.__decorate([
    Injectable({
        providedIn: 'root'
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], ProdownerserviceService);
export { ProdownerserviceService };
//# sourceMappingURL=prodownerservice.service.js.map
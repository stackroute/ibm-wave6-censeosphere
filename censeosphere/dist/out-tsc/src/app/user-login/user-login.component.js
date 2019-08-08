import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { UserService } from '../user.service';
let UserLoginComponent = class UserLoginComponent {
    constructor(loginService, route, router, userService) {
        this.loginService = loginService;
        this.route = route;
        this.router = router;
        this.userService = userService;
        this.arrayOfUser = [];
        this.user = new User();
    }
    ngOnInit() {
    }
    login(username, password) {
        this.user.emailId = username;
        this.user.password = password;
        console.log(this.user.emailId);
        console.log(this.user.password);
        this.userService.login(this.user).
            subscribe(data => {
            alert("Valid");
            console.log("successful", data);
        }, error => {
            alert("invalid Credentials");
            console.log("Error", error);
        });
    }
};
UserLoginComponent = tslib_1.__decorate([
    Component({
        selector: 'app-user-login',
        templateUrl: './user-login.component.html',
        styleUrls: ['./user-login.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [LoginService, ActivatedRoute, Router, UserService])
], UserLoginComponent);
export { UserLoginComponent };
//# sourceMappingURL=user-login.component.js.map
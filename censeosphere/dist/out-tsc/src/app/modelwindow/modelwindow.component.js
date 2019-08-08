import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
let ModelwindowComponent = class ModelwindowComponent {
    constructor(router) {
        this.router = router;
    }
    ngOnInit() {
    }
    account() {
        this.router.navigateByUrl("/");
    }
};
ModelwindowComponent = tslib_1.__decorate([
    Component({
        selector: 'app-modelwindow',
        templateUrl: './modelwindow.component.html',
        styleUrls: ['./modelwindow.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router])
], ModelwindowComponent);
export { ModelwindowComponent };
//# sourceMappingURL=modelwindow.component.js.map
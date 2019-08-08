import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router } from '@angular/router';
let Card2Component = class Card2Component {
    constructor(router, reviewService) {
        this.router = router;
        this.reviewService = reviewService;
    }
    ngOnInit() {
        this.product = JSON.parse(sessionStorage.getItem('data123'));
        console.log("in card component" + this.product);
        this.reviewService.getAllReviewsbyName(this.product.productName).subscribe((data) => {
            console.log(JSON.stringify(data));
            this.reviewsgiven = data.length;
            console.log("length of product list", this.reviewsgiven);
        });
    }
};
Card2Component = tslib_1.__decorate([
    Component({
        selector: 'app-card2',
        templateUrl: './card2.component.html',
        styleUrls: ['./card2.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, ReviewService])
], Card2Component);
export { Card2Component };
//# sourceMappingURL=card2.component.js.map
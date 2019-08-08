import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
let CardComponent = class CardComponent {
    constructor(router, reviewService, config) {
        this.router = router;
        this.reviewService = reviewService;
        this.config = config;
        config.max = 5;
        config.readonly = true;
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
    imageclick(product) {
        let a = JSON.stringify(product);
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        console.log("product info in card : " + JSON.stringify(product));
        sessionStorage.setItem('data', a);
        this.router.navigateByUrl("/productreview");
    }
};
CardComponent = tslib_1.__decorate([
    Component({
        selector: 'app-card',
        templateUrl: './card.component.html',
        styleUrls: ['./card.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, ReviewService, NgbRatingConfig])
], CardComponent);
export { CardComponent };
//# sourceMappingURL=card.component.js.map
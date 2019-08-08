import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { UpdateProfileService } from '../update-profile.service';
import { ProdownerserviceService } from '../prodownerservice.service';
import { ProductService } from '../product.service';
import { RecommendationService } from '../recommendation.service';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import subs from '../../assets/json/subCategory.json';
import { HttpClient } from '@angular/common/http';
let ReviwerdashComponent = class ReviwerdashComponent {
    constructor(router, landingpageservice, updates, route1, prodownerservice, productService, recommendationService, reviewService, config, http) {
        this.router = router;
        this.landingpageservice = landingpageservice;
        this.updates = updates;
        this.route1 = route1;
        this.prodownerservice = prodownerservice;
        this.productService = productService;
        this.recommendationService = recommendationService;
        this.reviewService = reviewService;
        this.config = config;
        this.http = http;
        this.productDetails = [];
        this.productByFamily = [];
        this.products = [];
        this.productDetails1 = [];
        this.reviewsgiven = [];
        this.subs = subs;
        this.array = [];
        config.max = 5;
        config.readonly = true;
    }
    ngOnInit() {
        this.landingpageservice.getAllProducts().subscribe((data) => {
            console.log(data);
            this.productDetails = data;
        });
        this.reviewerDetails();
        this.http.get('./assets/json/subCategory.json').subscribe((data) => {
            console.log(data, "Is this comming ???");
            this.array = data;
        });
        this.landingpageservice.getAllSubCategories().subscribe((data) => {
            console.log("inside get all ts file" + data);
            this.array = data;
            sessionStorage.setItem('sdata', data);
        });
        this.reviewerDetails();
        this.recommendationService.getProductBySubCategory(sessionStorage.getItem('reviewerEmail')).
            subscribe((data) => {
            console.log("ppppppppppppppprrrrrrrrrrrrriiiiiiiiii", data);
            data.map((e, i) => {
                this.productService.getProduct(e.productName).subscribe((productDetails) => {
                    this.reviewService.getAllReviewsbyName(productDetails.productName).subscribe((allReviews) => {
                        productDetails.size = allReviews.length;
                        this.productDetails.push(productDetails);
                    });
                });
            });
            console.log("in product by family  : ", this.productDetails);
        });
    }
    update() {
        this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword");
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    onClickSubCategory(subCategory) {
        console.log("in landing page", subCategory);
        this.landingpageservice.findAllProductsBySubcategory(subCategory).
            subscribe(data => {
            console.log("Product list : ", data);
            this.router.navigateByUrl("/productlist/" + subCategory);
        });
    }
    search(product) {
        console.log(product);
        this.productService.getProduct(product).subscribe(data => {
            let a = JSON.stringify(data);
            console.log("product info in rdashboard : ", JSON.stringify(data));
            sessionStorage.setItem('data', a);
            this.router.navigateByUrl("/rsearch");
        });
    }
    reviewerDetails() {
        const emailId = sessionStorage.getItem('reviewerEmail');
        console.log("Reviewer profile " + emailId);
        // sessionStorage.setItem("remailId",emailId);
        this.updates.getReviewerDetails(emailId).subscribe((data) => {
            console.log(data);
            sessionStorage.setItem("data1", JSON.stringify(data));
            console.log("inside reviewerdash" + data);
            // sessionStorage.setItem("data", JSON.stringify(data));
        });
    }
    imageclick(product) {
        let a = JSON.stringify(product);
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        console.log("product info from recommendation : " + JSON.stringify(product));
        sessionStorage.setItem('data', a);
        this.router.navigateByUrl("/rsearch");
    }
};
ReviwerdashComponent = tslib_1.__decorate([
    Component({
        selector: 'app-reviwerdash',
        templateUrl: './reviwerdash.component.html',
        styleUrls: ['./reviwerdash.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, LandingpageService,
        UpdateProfileService, ActivatedRoute,
        ProdownerserviceService, ProductService,
        RecommendationService, ReviewService,
        NgbRatingConfig, HttpClient])
], ReviwerdashComponent);
export { ReviwerdashComponent };
//# sourceMappingURL=reviwerdash.component.js.map
import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { ReviewService } from '../review.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { Writereview } from '../writereview';
import { RecommendationService } from '../recommendation.service';
import { UpdateProfileService } from '../update-profile.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
let ReviewComponent = class ReviewComponent {
    // constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService,private activatedRoute:ActivatedRoute) { 
    // this.reviews=[];
    constructor(reviewService, router, landingservice, activatedRoute, updates, route1, recommendationService, config) {
        this.reviewService = reviewService;
        this.router = router;
        this.landingservice = landingservice;
        this.activatedRoute = activatedRoute;
        this.updates = updates;
        this.route1 = route1;
        this.recommendationService = recommendationService;
        this.config = config;
        this.writereview = new Writereview();
        config.max = 5;
        config.readonly = true;
        // this.reviews=[];
    }
    // constructor(private reviewService:ReviewService,private router:Router,private landingservice:LandingpageService) { 
    //   this.reviews=[];
    ngOnInit() {
        console.log(" Data on review page :", JSON.parse(sessionStorage.getItem('data')));
        this.landingservice.getAllProducts().subscribe((data) => {
            console.log(data);
            this.productDetails = data;
        });
        this.reviewService.getAllReviews().subscribe((data) => {
            console.log(data);
            this.reviews = data;
        });
        this.product = JSON.parse(sessionStorage.getItem('data'));
        // this.reviewerDetails();
    }
    onSubmit(reviewTitle, reviewDesc) {
        let date;
        date = sessionStorage.getItem('uploadedOn');
        this.writereview.reviewTitle = reviewTitle;
        this.writereview.reviewDescription = reviewDesc;
        this.writereview.productName = JSON.parse(sessionStorage.getItem('data')).productName;
        this.writereview.price = JSON.parse(sessionStorage.getItem('data')).price;
        // this.writereview.reviewedOn=JSON.parse(sessionStorage.getItem('data')).uploadedOn;
        this.writereview.reviewerEmail = sessionStorage.getItem('reviewerEmail');
        this.writereview.subCategory = JSON.parse(sessionStorage.getItem('data')).subCategory;
        this.writereview.creditpoints = JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
        let family = JSON.parse(sessionStorage.getItem('data')).productFamily;
        let subcategory = this.writereview.subCategory;
        this.reviewService.addReview(this.writereview).
            subscribe(data => {
            console.log("data stored successfully");
        });
        this.router.navigateByUrl("/reviewerdash");
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    update() {
        this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword");
    }
    account() {
        this.router.navigateByUrl("/");
    }
};
ReviewComponent = tslib_1.__decorate([
    Component({
        selector: 'app-review',
        templateUrl: './review.component.html',
        styleUrls: ['./review.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [ReviewService, Router, LandingpageService,
        ActivatedRoute, UpdateProfileService,
        ActivatedRoute,
        RecommendationService, NgbRatingConfig])
], ReviewComponent);
export { ReviewComponent };
//# sourceMappingURL=review.component.js.map
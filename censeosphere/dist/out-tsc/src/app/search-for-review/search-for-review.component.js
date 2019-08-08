import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchForReviewService } from '../search-for-review.service';
import { ReviewerdetailsService } from '../reviewerdetails.service';
import { Reviewerone } from '../reviewerone';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import subs from '../../assets/json/subCategory.json';
import { HttpClient } from '@angular/common/http';
import { LandingpageService } from '../landingpage.service';
let SearchForReviewComponent = class SearchForReviewComponent {
    constructor(router, searchforreview, activatedRoute, reviewerdetail, reviewService, config, http, landingpageservice) {
        this.router = router;
        this.searchforreview = searchforreview;
        this.activatedRoute = activatedRoute;
        this.reviewerdetail = reviewerdetail;
        this.reviewService = reviewService;
        this.config = config;
        this.http = http;
        this.landingpageservice = landingpageservice;
        this.reviews = [];
        this.productName = "";
        this.price = "";
        this.reviewedOn = "";
        this.revieweremail = "";
        this.productname = "";
        this.reviewerone = new Reviewerone();
        this.reviewerinfo1 = [];
        this.subs = subs;
        this.array = [];
        config.max = 5;
        config.readonly = true;
    }
    ngOnInit() {
        this.http.get('./assets/json/subCategory.json').subscribe((data) => {
            console.log(data, "Is this comming ???");
            this.array = data;
        });
        this.landingpageservice.getAllSubCategories().subscribe((data) => {
            console.log("inside get all ts file" + data);
            this.array = data;
            sessionStorage.setItem('sdata', data);
        });
        this.revieweremail = sessionStorage.getItem('reviewerEmail');
        console.log("email in write a review" + this.revieweremail);
        this.productname = JSON.parse(sessionStorage.getItem('data')).productName;
        console.log("productname in  write a review" + this.productname);
        this.reviewerdetail.getReviewer(this.revieweremail).subscribe((data) => {
            let a = JSON.stringify(data);
            console.log("reviewer data in search component" + JSON.stringify(data));
            sessionStorage.setItem('rdata', a);
            this.reviewerinfo = data;
            console.log("reviewer data in search component" + JSON.stringify(this.reviewerinfo));
        });
        this.reviewService.getAllReviewsbyName(this.productname).subscribe((data) => {
            console.log("review details in search" + JSON.stringify(data));
            this.reviewdetails = data;
            console.log(JSON.stringify(this.reviewdetails));
            for (let i = 0; i < data.length; i++) {
                let image = data[i].reviewerEmail;
                console.log("emailId", image);
                this.reviewerdetail.getReviewer(image).subscribe((data) => {
                    console.log("reviewer data in search componentttttttttttttt" + JSON.stringify(data));
                    this.reviewerinfo1.push(data);
                    this.reviewdetails = this.reviewdetails.map((e, i) => {
                        if (e.reviewerEmail === data.emailId) {
                            e.image = data.image;
                        }
                        console.log(e, "is this object has image ??");
                        return e;
                    });
                    console.log("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", this.reviewerinfo1);
                });
            }
        });
        this.products = JSON.parse(sessionStorage.getItem('data'));
    }
    yes(email) {
        console.log("inside yes " + email);
        this.reviewerdetail.getReviewer(email).subscribe((data) => {
            let a = JSON.stringify(data);
            console.log("reviewer data in yes method" + JSON.stringify(data));
            sessionStorage.setItem('rdata', a);
            this.reviewerone = data;
            console.log("reviewer data in yes method " + JSON.stringify(this.reviewerone));
            this.score = JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
            console.log("reviewer score" + this.score);
            this.score = this.score + 5;
            this.reviewerone.creditpoints = this.score;
            console.log("reviewer score after adding" + this.score);
            this.reviewerdetail.updateReviewer(this.reviewerone, email).subscribe((data) => {
                console.log("updated reviewer data in yes method" + JSON.stringify(data));
            });
        });
    }
    no(email) {
        console.log("inside yes" + email);
        this.reviewerdetail.getReviewer(email).subscribe((data) => {
            let a = JSON.stringify(data);
            console.log("reviewer data in no method" + JSON.stringify(data));
            sessionStorage.setItem('rdata', a);
            this.reviewerone = data;
            console.log("reviewer data in no method " + JSON.stringify(this.reviewerone));
            this.score = JSON.parse(sessionStorage.getItem('rdata')).creditpoints;
            console.log("reviewer score" + this.score);
            this.score = this.score - 5;
            this.reviewerone.creditpoints = this.score;
            console.log("reviewer score after adding" + this.score);
            this.reviewerdetail.updateReviewer(this.reviewerone, email).subscribe((data) => {
                console.log("updated reviewer data in no method" + JSON.stringify(data));
            });
        });
    }
    wreview() {
        this.router.navigateByUrl("/writereview");
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
};
SearchForReviewComponent = tslib_1.__decorate([
    Component({
        selector: 'app-search-for-review',
        templateUrl: './search-for-review.component.html',
        styleUrls: ['./search-for-review.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, SearchForReviewService,
        ActivatedRoute, ReviewerdetailsService,
        ReviewService, NgbRatingConfig,
        HttpClient, LandingpageService])
], SearchForReviewComponent);
export { SearchForReviewComponent };
//# sourceMappingURL=search-for-review.component.js.map
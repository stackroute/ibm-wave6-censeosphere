import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { Authentication } from '../authentication';
import { LoginvalidationService } from '../loginvalidation.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import subs from 'src/assets/json/subCategory.json';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
const helper = new JwtHelperService();
let LandingPageComponent = class LandingPageComponent {
    constructor(http, router, landingpageservice, loginvalidation, productService, reviewService, config) {
        this.http = http;
        this.router = router;
        this.landingpageservice = landingpageservice;
        this.loginvalidation = loginvalidation;
        this.productService = productService;
        this.reviewService = reviewService;
        this.config = config;
        this.showFiller = false;
        this.productDetails = [];
        this.categories = [];
        this.subCategories = [];
        this.job = "";
        this.reviewsgiven = [];
        this.productname = "";
        this.productname1 = "";
        // validatingForm: FormGroup;
        this.productDetails1 = [];
        this.reviewsgiven1 = [];
        this.subs = subs;
        this.array = [];
        this.form = new FormGroup({
            emailId: new FormControl('', [Validators.required, Validators.email]),
            password: new FormControl('', Validators.required)
        });
        this.helper = new JwtHelperService();
        this.auth = new Authentication();
        config.max = 5;
        config.readonly = true;
    }
    ngOnInit() {
        this.landingpageservice.getRecentProducts().subscribe((data) => {
            console.log(data);
            data.map((e, i) => {
                this.reviewService.getAllReviewsbyName(e.productName).subscribe((review) => {
                    e.size = review.length;
                    this.productDetails.push(e);
                });
            });
        });
        this.landingpageservice.getTrendingProducts().subscribe((data1) => {
            console.log(data1);
            data1.map((e, i) => {
                this.reviewService.getAllReviewsbyName(e.productName).subscribe((reviews) => {
                    e.size = reviews.length;
                    this.productDetails1.push(e);
                });
            });
        });
        this.http.get('./assets/json/subCategory.json').subscribe((data) => {
            // console.log(data, "Is this comming ???");
            this.array = data;
        });
        //  this.landingpageservice.getAllSubCategories().subscribe((data: any) => {
        //   // console.log("inside get all ts file"+data);
        //   this.array= data;
        //   sessionStorage.setItem('sdata',data);
        // })
        this.landingpageservice.getAllCategory().subscribe((data) => {
            console.log(data);
            this.categories = data;
        });
        this.landingpageservice.getAllSubCategories().subscribe((data) => {
            console.log(data);
            this.subCategories = data;
        });
    }
    onClick(role) {
        console.log(role);
        this.router.navigateByUrl("/account/" + role);
    }
    onClickPO(role1) {
        console.log(role1);
        this.router.navigateByUrl("/account/" + role1);
    }
    reviewer(emailId, password) {
        this.auth.emailId = emailId;
        this.auth.password = password;
        this.loginvalidation.login(this.auth).
            subscribe((data) => {
            console.log("data from backend ", data.token);
            if (data.token) {
                console.log("in if");
                let role = this.helper.decodeToken(data.token).sub;
                console.log("we are having this......", data.token);
                console.log("in if print email   " + emailId);
                console.log("in if print password   " + password);
                console.log("in if print role   ", role);
                sessionStorage.setItem('reviewerEmail', emailId);
                if (role == this.job) {
                    console.log(role);
                    console.log("in if1");
                    this.router.navigateByUrl("/reviewerdash");
                }
                else {
                    alert("provide valid credentailds");
                }
            }
        });
    }
    productOwner(emailId, password) {
        this.auth.emailId = emailId;
        this.auth.password = password;
        this.loginvalidation.login(this.auth).
            subscribe((data) => {
            console.log("data from backend ", data.token);
            if (data.token) {
                console.log("in if");
                let role = this.helper.decodeToken(data.token).sub;
                console.log("we are having this......", data.token);
                console.log("in if print email   " + emailId);
                console.log("in if print password   " + password);
                console.log("in if print role   ", role);
                sessionStorage.setItem('productOwnerEmail', emailId);
                if (role == this.job) {
                    console.log(role);
                    console.log("in if1");
                    console.log(emailId);
                    this.router.navigateByUrl("/productownerdashboard/" + emailId);
                }
                else {
                    alert("provide valid credentailds");
                }
            }
        });
    }
    onclick(rrole) {
        console.log(rrole);
        this.job = rrole;
    }
    onclick1(prole) {
        console.log(prole);
        this.job = prole;
    }
    newlogin(lemailId, lpassword) {
        this.auth.emailId = lemailId;
        this.auth.password = lpassword;
        this.loginvalidation.login(this.auth).
            subscribe((data) => {
            console.log("data from backend ", data.token);
            if (data.token) {
                console.log("in if");
                let role = this.helper.decodeToken(data.token).sub;
                console.log("we are having this......", data.token);
                console.log("in if print email   " + lemailId);
                console.log("in if print password   " + lpassword);
                console.log("in if print role   ", role);
                if (role == 'reviewer') {
                    console.log(role);
                    console.log("in if1");
                    sessionStorage.setItem('reviewerEmail', lemailId);
                    this.router.navigateByUrl("/reviewerdash");
                }
                else if (role == 'product-owner') {
                    sessionStorage.setItem('productOwnerEmail', lemailId);
                    this.router.navigateByUrl("/productownerdashboard");
                }
            }
        }, error => {
            alert("Invalid credential");
        });
    }
    onClickSubCategory(subCategory) {
        // console.log("in landing page",subCategory);
        this.landingpageservice.findAllProductsBySubcategory(subCategory).
            subscribe(data => {
            // console.log("Product list : ",data);
            this.router.navigateByUrl("/productlistguest/" + subCategory);
        });
    }
    searchproductL(product) {
        // console.log(product);
        this.productService.getProduct(product).
            subscribe(data => {
            // console.log("product info : ", data);
            let a = JSON.stringify(data);
            sessionStorage.setItem('data123', a);
            this.showComponent = true;
        }, error => {
            this.showComponent1 = true;
        });
    }
    imageclick(product) {
        let a = JSON.stringify(product);
        // console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        // console.log("product info in card : " + JSON.stringify(product));
        sessionStorage.setItem('data', a);
        this.router.navigateByUrl("/productreview");
    }
};
LandingPageComponent = tslib_1.__decorate([
    Component({
        selector: 'app-landing-page',
        templateUrl: './landing-page.component.html',
        styleUrls: ['./landing-page.component.css'],
    }),
    tslib_1.__metadata("design:paramtypes", [HttpClient, Router, LandingpageService,
        LoginvalidationService, ProductService,
        ReviewService, NgbRatingConfig])
], LandingPageComponent);
export { LandingPageComponent };
//# sourceMappingURL=landing-page.component.js.map
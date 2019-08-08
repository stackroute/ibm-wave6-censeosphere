import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import pro from 'src/assets/json/data1.json';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginvalidationService } from '../loginvalidation.service';
import { ProductService } from '../product.service';
import { HttpClient } from '@angular/common/http';
import { LandingpageService } from '../landingpage.service';
let ProductlistComponent = class ProductlistComponent {
    constructor(router, loginvalidation, productService, http, activatedRoute, landingpageService) {
        this.router = router;
        this.loginvalidation = loginvalidation;
        this.productService = productService;
        this.http = http;
        this.activatedRoute = activatedRoute;
        this.landingpageService = landingpageService;
        this.array = [];
        this.subCategory = String;
        this.pro = pro;
    }
    ngOnInit() {
        this.http.get('./assets/json/subCategory.json').subscribe((data) => {
            console.log(data, "Is this comming ???");
            this.array = data;
        });
        this.activatedRoute.params.subscribe(params => {
            // console.log(params);
            this.subCategory = params['subCategory'];
            console.log(" from productlit componenent " + this.subCategory);
            this.call(this.subCategory);
        });
        // this.http.get('./assets/json/data1.json').subscribe((data:any) => {
        // console.log(data, "Is list there ???");
        // this.listofproducts = data;
        // })
    }
    call(value) {
        console.log(value);
        this.landingpageService.findAllProductsBySubcategory(value).
            subscribe(data => {
            this.listofproducts = data;
            console.log("inside  method call" + this.listofproducts);
        });
    }
    // openSignin(){
    //   console.log("Sign in opened")
    //   this.landingpageService.
    // }
    onClickSubCategory(subCategory) {
        console.log(subCategory);
        this.subCategory = subCategory;
        this.landingpageService.findAllProductsBySubcategory(subCategory).
            subscribe(data => {
            this.listofproducts = data;
            console.log("inside  method onclick" + this.listofproducts);
        });
    }
    update() {
        this.router.navigateByUrl("/rprofile/name/gmail/reconfirmpassword");
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    imageclickguest(pro) {
        let a = JSON.stringify(pro);
        sessionStorage.setItem('data', a);
        this.router.navigateByUrl("/productreview");
    }
};
ProductlistComponent = tslib_1.__decorate([
    Component({
        selector: 'app-productlist',
        templateUrl: './productlist.component.html',
        styleUrls: ['./productlist.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, LoginvalidationService,
        ProductService, HttpClient, ActivatedRoute,
        LandingpageService])
], ProductlistComponent);
export { ProductlistComponent };
//# sourceMappingURL=productlist.component.js.map
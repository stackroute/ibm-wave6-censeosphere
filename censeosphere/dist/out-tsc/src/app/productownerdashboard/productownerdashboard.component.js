import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProdownerserviceService } from '../prodownerservice.service';
import { LandingpageService } from '../landingpage.service';
import { UpdateProfileService } from '../update-profile.service';
import { ProductService } from '../product.service';
//import { getMaxListeners } from 'cluster';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
let ProductownerdashboardComponent = class ProductownerdashboardComponent {
    constructor(updates, route1, router, prodownerservice, landingpageservice, productService, config) {
        this.updates = updates;
        this.route1 = route1;
        this.router = router;
        this.prodownerservice = prodownerservice;
        this.landingpageservice = landingpageservice;
        this.productService = productService;
        this.config = config;
        // productOwners: any;
        this.productOwners = [];
        this.products = [];
        this.products1 = [];
        this.productDetails = [];
        this.listofproducts = [];
        config.max = 5;
        config.readonly = true;
    }
    ngOnInit() {
        this.prodownerservice.getAllProductOwners().subscribe((data) => {
            console.log(data);
            this.productOwners = data;
        });
        this.landingpageservice.getAllProducts().subscribe((data) => {
            console.log(data);
            this.productDetails = data;
        });
        this.productOwnerDetails();
        //  this.myproducts.productsadded.forEach((y:any) => { 
        //   console.log(y);
        //  })
        //  console.log("new list"+this.myproducts.productsadded.size());
        //  for(let i=0;i< this.myproducts.productsadded.length();i++)
        //  {
        //  this.listofproducts.push(this.myproducts.productsadded[i]);
        //  }
    }
    add() {
        this.router.navigateByUrl("/add-product");
    }
    update() {
        // const name=this.reviewer.name;
        //const emailId=this.reviewer.emailId;
        // const reconfirmPassword=this.reviewer.reconfirmPassword;
        // console.log("emailId" +emailId);
        this.router.navigateByUrl('/prodownerupdateprofile/name/gmail/reconfirmpassword');
        // this.router.navigateByUrl("/prodownerupdateprofile");
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    productOwnerDetails() {
        // const emailId=this.route1.snapshot.paramMap.get('emailId');
        const emailId = sessionStorage.getItem("productOwnerEmail");
        console.log("product Owner profile " + emailId);
        sessionStorage.setItem("pemailId", emailId);
        this.updates.getProductOwnerDetails(emailId).subscribe((data) => {
            console.log(data);
            // sessionStorage.setItem("pdata", JSON.stringify(data));
            console.log("inside update" + JSON.stringify(data));
            this.listofproducts = data.productsadded;
            this.listofproducts.forEach((y) => {
                console.log(y);
            });
        });
    }
    deleteProduct(product) {
        console.log(product);
        this.productService.deleteProduct(product).
            subscribe((data) => {
            console.log("product info : ", data);
        });
        this.listofproducts = this.listofproducts.filter(e => {
            return e.productName !== product.productName;
        });
        //  this.listofproducts.pop();
    }
    account() {
        this.router.navigateByUrl("/productownerdashboard");
    }
    searchproductO(product) {
        this.productService.searchProductByProductOwner(sessionStorage.getItem('productOwnerEmail'), product).
            subscribe(data => {
            if (data == null) {
                this.showComponent1 = true;
            }
            else {
                console.log("product info  : ", data);
                let a = JSON.stringify(data);
                sessionStorage.setItem('data123', a);
                this.showComponent = true;
            }
        });
    }
};
ProductownerdashboardComponent = tslib_1.__decorate([
    Component({
        selector: 'app-productownerdashboard',
        templateUrl: './productownerdashboard.component.html',
        styleUrls: ['./productownerdashboard.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [UpdateProfileService, ActivatedRoute,
        Router, ProdownerserviceService,
        LandingpageService, ProductService, NgbRatingConfig])
], ProductownerdashboardComponent);
export { ProductownerdashboardComponent };
//# sourceMappingURL=productownerdashboard.component.js.map
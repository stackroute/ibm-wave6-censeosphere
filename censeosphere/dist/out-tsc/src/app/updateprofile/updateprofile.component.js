import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LandingpageService } from '../landingpage.service';
import { SearchForReviewService } from '../search-for-review.service';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { UpdateProfile } from '../update-profile';
import { UpdateProfileService } from '../update-profile.service';
import { ProductService } from '../product.service';
import { ReviewService } from '../review.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
let UpdateprofileComponent = class UpdateprofileComponent {
    constructor(router, landingpageservice, searchforreview, http, _formBuilder, updates, productservice, reviewService, config) {
        this.router = router;
        this.landingpageservice = landingpageservice;
        this.searchforreview = searchforreview;
        this.http = http;
        this._formBuilder = _formBuilder;
        this.updates = updates;
        this.productservice = productservice;
        this.reviewService = reviewService;
        this.config = config;
        this.productname = "";
        this.email = "";
        this.reviewsgiven = [];
        this.update1 = new UpdateProfile();
        this.reviews = [];
        config.max = 5;
        config.readonly = true;
    }
    ngOnInit() {
        this.firstFormGroup = this._formBuilder.group({
            imageCtrl: ['', Validators.required],
            NameCtrl: ['', Validators.required],
            passwordCtrl: ['', [Validators.required, Validators.minLength(5)]],
            ReConfirmPasswordCtrl: ['', Validators.required],
            emailCtrl: ['', Validators.required],
        });
        this.reviewer = JSON.parse(sessionStorage.getItem("data1"));
        console.log("Reviewer info. :", this.reviewer);
        console.log("reviewer info2", this.reviewer.revieweswritten);
        this.reviews = this.reviewer.revieweswritten;
        this.reviews.forEach((y) => {
            console.log("review list:", y);
        });
        for (let i = 0; i < (this.reviews).length; i++) {
            this.productname = this.reviews[i].productName;
            console.log(this.productname);
            this.productservice.getProduct(this.productname).subscribe((data) => {
                console.log("product image", data);
                this.productDetails = data;
                this.reviews = this.reviews.map((e, i) => {
                    if (e.productName === data.productName) {
                        e.image = data.image;
                        e.productName = data.productName;
                        e.rating = data.rating;
                        e.price = data.price;
                    }
                    console.log(e, "is this object has image ??");
                    return e;
                });
            });
            this.reviewService.getAllReviewsbyName(this.productname).subscribe((data) => {
                console.log("priyanka" + JSON.stringify(data));
                this.reviewsgiven = data;
                let name = this.productname;
                console.log("length of product list", this.reviewsgiven.length);
                this.reviews = this.reviews.map((e, j) => {
                    if (e.productName === data[0].productName) {
                        e.size = this.reviewsgiven.length;
                    }
                    console.log(e, "list size");
                    return e;
                });
            });
        }
    }
    updateDetails() {
        this.email = sessionStorage.getItem('reviewerEmail');
        console.log("from session" + this.email);
        this.update1.emailId = this.firstFormGroup.controls.emailCtrl.value;
        this.update1.name = this.firstFormGroup.controls.NameCtrl.value;
        this.update1.image = this.mediaName;
        this.update1.reconfirmPassword = this.firstFormGroup.controls.ReConfirmPasswordCtrl.value;
        this.update1.creditpoints = JSON.parse(sessionStorage.getItem('data1')).creditpoints;
        console.log(this.update1.emailId);
        console.log(this.update1.name);
        console.log(this.update1.image);
        console.log(this.update1.reconfirmPassword);
        console.log("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp", this.update1.creditpoints);
        this.updates.updateReviewerDetails(this.update1, this.email).
            subscribe(data => {
            console.log("successfully updated");
        });
    }
    update() {
        this.router.navigateByUrl("/rprofile");
    }
    lpage() {
        this.router.navigateByUrl("/returnlanding");
    }
    close() {
        this.router.navigateByUrl("/reviewerdash");
    }
    selectVideo(event) {
        this.selectedVideo = event.target.files;
    }
    uploadVideo() {
        this.currentFileUpload = this.selectedVideo.item(0);
        this.mediaName = this.currentFileUpload.name;
    }
};
UpdateprofileComponent = tslib_1.__decorate([
    Component({
        selector: 'app-updateprofile',
        templateUrl: './updateprofile.component.html',
        styleUrls: ['./updateprofile.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, LandingpageService,
        SearchForReviewService, HttpClient,
        FormBuilder, UpdateProfileService, ProductService,
        ReviewService, NgbRatingConfig])
], UpdateprofileComponent);
export { UpdateprofileComponent };
//# sourceMappingURL=updateprofile.component.js.map
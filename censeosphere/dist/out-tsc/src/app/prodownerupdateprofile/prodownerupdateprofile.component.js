import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { UpdateProfile } from '../update-profile';
import { UpdateProfileService } from '../update-profile.service';
let ProdownerupdateprofileComponent = class ProdownerupdateprofileComponent {
    constructor(router, http, _formBuilder, updates, route1) {
        this.router = router;
        this.http = http;
        this._formBuilder = _formBuilder;
        this.updates = updates;
        this.route1 = route1;
        this.email = "";
        this.update = new UpdateProfile();
    }
    ngOnInit() {
        // const name=this.route1.snapshot.paramMap.get('name');
        // const emailId=this.route1.snapshot.paramMap.get('emailId');
        // const reconfirmPassword=this.route1.snapshot.paramMap.get('reconfirmPassword');
        this.firstFormGroup = this._formBuilder.group({
            //  imageCtrl: ['', Validators.required],
            NameCtrl: ['', Validators.required],
            ReConfirmPasswordCtrl: ['', Validators.required],
            emailCtrl: ['', Validators.required],
        });
        //  console.log(JSON.parse(sessionStorage.getItem("data")))
        this.productOwner = JSON.parse(sessionStorage.getItem("pdata"));
        console.log(this.productOwner);
    }
    updateDetails() {
        this.email = sessionStorage.getItem("pemailId");
        console.log("from session" + this.email);
        this.update.emailId = this.firstFormGroup.controls.emailCtrl.value;
        this.update.name = this.firstFormGroup.controls.NameCtrl.value;
        this.update.image = this.mediaName;
        this.update.reconfirmPassword = this.firstFormGroup.controls.ReConfirmPasswordCtrl.value;
        console.log(this.update.emailId);
        console.log(this.update.name);
        console.log(this.update.image);
        console.log(this.update.reconfirmPassword);
        console.log(this.update);
        console.log(this.email);
        this.updates.updateProductOwnerDetails(this.update, this.email).subscribe(data => {
            console.log("successfully updated");
        });
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    account() {
        this.router.navigateByUrl("/productownerdashboard");
    }
    selectVideo(event) {
        this.selectedVideo = event.target.files;
    }
    uploadVideo() {
        this.currentFileUpload = this.selectedVideo.item(0);
        this.mediaName = this.currentFileUpload.name;
    }
};
ProdownerupdateprofileComponent = tslib_1.__decorate([
    Component({
        selector: 'app-prodownerupdateprofile',
        templateUrl: './prodownerupdateprofile.component.html',
        styleUrls: ['./prodownerupdateprofile.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, HttpClient,
        FormBuilder, UpdateProfileService, ActivatedRoute])
], ProdownerupdateprofileComponent);
export { ProdownerupdateprofileComponent };
//# sourceMappingURL=prodownerupdateprofile.component.js.map
import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators, FormControl } from '@angular/forms';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { map, startWith } from 'rxjs/operators';
let AddProductComponent = class AddProductComponent {
    constructor(router, http, _formBuilder, productDetails, route) {
        this.router = router;
        this.http = http;
        this._formBuilder = _formBuilder;
        this.productDetails = productDetails;
        this.route = route;
        this.controls = new FormControl();
        this.options = ['Air Conditioner', 'Mobile Phone', 'Camera', 'Headphone',
            'Laptop', 'Refrigerator', 'Washing Machine', 'Speaker'];
        this.control = new FormControl();
        this.categorys = ['Electronic Devices', 'Education', 'Hospital', 'Banking', 'Business'];
        this.product = new Product();
        this.array = [];
    }
    _filter2(value) {
        const filterValue = value.toLowerCase();
        return this.options.filter(option => option.toLowerCase().includes(filterValue));
    }
    _filter1(value) {
        const filterValue = value.toLowerCase();
        return this.categorys.filter(category => category.toLowerCase().includes(filterValue));
    }
    ngOnInit() {
        this.filteredOptions = this.controls.valueChanges.pipe(startWith(''), map(value => this._filter2(value)));
        this.filteredCategorys = this.control.valueChanges.pipe(startWith(''), map(value => this._filter1(value)));
        this.firstFormGroup = this._formBuilder.group({
            CategoryCtrl: ['', Validators.required],
            SubCategoryCtrl: ['', Validators.required],
            ProductNameCtrl: ['', Validators.required],
            ProductFamilyCtrl: ['', Validators.required],
            ProductPriceCtrl: ['', Validators.required],
            ProductSpecificationsCtrl: ['', Validators.required],
            ProductDescriptionCtrl: ['', Validators.required],
            ProductImageCtrl: ['', Validators.required],
        });
    }
    saveProduct() {
        this.product.category = this.firstFormGroup.controls.CategoryCtrl.value;
        this.product.subCategory = this.firstFormGroup.controls.SubCategoryCtrl.value;
        this.product.productName = this.firstFormGroup.controls.ProductNameCtrl.value;
        this.product.productFamily = this.firstFormGroup.controls.ProductFamilyCtrl.value;
        this.product.price = this.firstFormGroup.controls.ProductPriceCtrl.value;
        this.product.specifications = this.firstFormGroup.controls.ProductSpecificationsCtrl.value;
        this.product.description = this.firstFormGroup.controls.ProductDescriptionCtrl.value;
        // this.product.image=this.firstFormGroup.controls.ProductImageCtrl.value;
        this.product.image = this.mediaName;
        this.product.rating = 0;
        this.product.addedby = sessionStorage.getItem('productOwnerEmail');
        console.log(this.product);
        environment: return this.productDetails.saveProduct(this.product).subscribe(data => {
            console.log(data);
            this.router.navigateByUrl("/productownerdashboard");
        });
    }
    lpage() {
        this.router.navigateByUrl("/");
    }
    update() {
        this.router.navigateByUrl("/prodownerupdateprofile/name/gmail/reconfirmpassword");
    }
    account() {
        this.router.navigateByUrl("/productownerdashboard");
    }
    selectPhoto(event) {
        this.selectedPhoto = event.target.files;
    }
    uploadPhoto() {
        this.currentFileUpload = this.selectedPhoto.item(0);
        this.mediaName = this.currentFileUpload.name;
    }
};
AddProductComponent = tslib_1.__decorate([
    Component({
        selector: 'app-add-product',
        templateUrl: './add-product.component.html',
        styleUrls: ['./add-product.component.css']
    }),
    tslib_1.__metadata("design:paramtypes", [Router, HttpClient,
        FormBuilder, ProductService,
        ActivatedRoute])
], AddProductComponent);
export { AddProductComponent };
//# sourceMappingURL=add-product.component.js.map
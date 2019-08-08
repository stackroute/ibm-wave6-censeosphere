import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { NewaccountComponent } from './newaccount/newaccount.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { UpdateprofileComponent } from './updateprofile/updateprofile.component';
import { ReviwerdashComponent } from './reviwerdash/reviwerdash.component';
import { SearchForReviewComponent } from './search-for-review/search-for-review.component';
import { ProductownerdashboardComponent } from './productownerdashboard/productownerdashboard.component';
import { ProdownerupdateprofileComponent } from './prodownerupdateprofile/prodownerupdateprofile.component';
import { AddProductComponent } from './add-product/add-product.component';
import { ReviewComponent } from './review/review.component';
import { ProductreviewComponent } from './productreview/productreview.component';
import { ProductlistComponent } from './productlist/productlist.component';
import { ProductlistGuestComponent } from './productlist-guest/productlist-guest.component';
const routes = [
    { path: '', component: LandingPageComponent },
    { path: 'productreview', component: ProductreviewComponent },
    { path: 'login', component: UserLoginComponent },
    { path: 'account/:role', component: NewaccountComponent },
    { path: 'rprofile/:name/:emailId/:reconfirmPassword', component: UpdateprofileComponent },
    { path: 'rprofile', component: UpdateprofileComponent },
    { path: 'reviwerdashboard', component: ReviwerdashComponent },
    { path: 'returnlanding', component: LandingPageComponent },
    { path: 'rsearch', component: SearchForReviewComponent },
    { path: 'productownerdashboard/:emailId', component: ProductownerdashboardComponent },
    { path: 'productownerdashboard', component: ProductownerdashboardComponent },
    { path: 'add-product', component: AddProductComponent },
    { path: 'reviewerdash', component: ReviwerdashComponent },
    { path: 'writereview', component: ReviewComponent },
    { path: 'writereview/:emailId', component: ReviewComponent },
    { path: 'searchreview', component: SearchForReviewComponent },
    { path: 'prodownerupdateprofile/:name/:emailId/:reconfirmPassword', component: ProdownerupdateprofileComponent },
    // {path:'rdashboard',component:ReviewerdashboardComponent},
    { path: 'productlist/:subCategory', component: ProductlistComponent },
    { path: 'productlistguest/:subCategory', component: ProductlistGuestComponent },
    { path: '**', component: LandingPageComponent }
];
let AppRoutingModule = class AppRoutingModule {
};
AppRoutingModule = tslib_1.__decorate([
    NgModule({
        imports: [RouterModule.forRoot(routes, { useHash: true })],
        exports: [RouterModule]
    })
], AppRoutingModule);
export { AppRoutingModule };
//# sourceMappingURL=app-routing.module.js.map
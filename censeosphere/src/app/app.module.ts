import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CustomMaterialModule } from './core/material.module';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatStepperModule, MatInputModule, MatButtonModule, MatCheckboxModule, MatAutocompleteModule, MAT_AUTOCOMPLETE_DEFAULT_OPTIONS} from '@angular/material'
import {MatSidenavModule} from '@angular/material/sidenav';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { LoginService } from './login.service';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { NewaccountComponent } from './newaccount/newaccount.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatTabsModule } from '@angular/material';
import { UpdateprofileComponent } from './updateprofile/updateprofile.component';
import { ReviwerdashComponent } from './reviwerdash/reviwerdash.component';
import { SearchForReviewComponent } from './search-for-review/search-for-review.component';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import { ProductownerdashboardComponent } from './productownerdashboard/productownerdashboard.component';
import { ProdownerupdateprofileComponent } from './prodownerupdateprofile/prodownerupdateprofile.component';
import { AddProductComponent } from './add-product/add-product.component';
import { ReviewComponent } from './review/review.component';
import { CardComponent } from './card/card.component';
import { ProductreviewComponent } from './productreview/productreview.component';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { Card2Component } from './card2/card2.component';
import { ModelwindowComponent } from './modelwindow/modelwindow.component';
import { ProductlistComponent } from './productlist/productlist.component';
import { ProductlistGuestComponent } from './productlist-guest/productlist-guest.component';

// import { MDBBootstrapModule } from 'angular-bootstrap-md';



@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    LandingPageComponent,
    NewaccountComponent,
    UpdateprofileComponent,
    ReviwerdashComponent,
    ProductownerdashboardComponent,
    ProdownerupdateprofileComponent,
    SearchForReviewComponent,
    AddProductComponent,
    ReviewComponent,
    CardComponent,
    ProductreviewComponent,
    Card2Component,
    ModelwindowComponent,
    ProductlistComponent,
    ProductlistGuestComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    MatStepperModule, 
    MatInputModule, 
    MatButtonModule,
    MatCheckboxModule,
    FormsModule,
    HttpClientModule,
    MatSidenavModule,
    ReactiveFormsModule,
    MatTabsModule,
    Ng2CarouselamosModule,
    MatAutocompleteModule,
    NgbModule
    
   
  ],
  providers: [{provide: MAT_AUTOCOMPLETE_DEFAULT_OPTIONS, useValue: {autoActiveFirstOption: false}},LoginService,NgbRatingConfig],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [MatButtonModule, MatCheckboxModule]
})
export class AppModule { }

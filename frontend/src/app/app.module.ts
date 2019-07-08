import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CustomMaterialModule } from './core/material.module';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatStepperModule, MatInputModule, MatButtonModule, MatCheckboxModule} from '@angular/material'
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
import { ProductownerDashboardComponent } from './productowner-dashboard/productowner-dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    LandingPageComponent,
    NewaccountComponent,
    UpdateprofileComponent,
    ReviwerdashComponent,
    ProductownerDashboardComponent
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
    MatTabsModule
  ],
  providers: [LoginService],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [MatButtonModule, MatCheckboxModule]
})
export class AppModule { }

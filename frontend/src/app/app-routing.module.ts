import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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
import { ReviewerdashboardComponent } from './reviewerdashboard/reviewerdashboard.component';
import { CardComponent } from './card/card.component';
const routes: Routes = [  
  { path: '',component:LandingPageComponent},
  { path: 'login', component:UserLoginComponent },
  { path: 'account/:role', component: NewaccountComponent},
  {path:'rprofile/:name/:emailId/:reconfirmPassword',component:UpdateprofileComponent},
  {path:'rprofile',component:UpdateprofileComponent},
  {path:'reviwerdashboard',component:ReviwerdashComponent},
  {path:'returnlanding',component:LandingPageComponent},
  {path: 'rsearch',component:SearchForReviewComponent},
  {path:'productownerdashboard/:emailId',component:ProductownerdashboardComponent},
  {path:'productownerdashboard',component:ProductownerdashboardComponent},
  {path:'add-product',component:AddProductComponent},
  // {path:'reviewerdash/:emailId',component:ReviwerdashComponent}
  // {path:'reviewerdash/:productByFamily',component:ReviwerdashComponent},
  {path:'reviewerdash/:family',component:ReviwerdashComponent},
  {path:'writereview',component:ReviewComponent},
  {path:'searchreview',component:SearchForReviewComponent},
  { path:'prodownerupdateprofile/:name/:emailId/:reconfirmPassword',component:ProdownerupdateprofileComponent},
  {path:'rdashboard',component:ReviewerdashboardComponent},
  {path:'',component:ReviwerdashComponent,pathMatch:'prefix',children:[{
    path:'card',component:CardComponent
  }]},
  // { path: '**',component:LandingPageComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

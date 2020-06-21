import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { appRoutes } from './routes';
import { MatDialogModule } from '@angular/material/dialog';

import { AuthService } from './auth/auth.service';
import { ToastrService } from './common/toastr.service';
import { CarsService } from './cars/cars.service';
import { NgbDate, NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http'; 

import { AppComponent } from './app.component';
import { Header } from './header/header';
import { HomePage } from './home-page/home-page';
import { RouterModule } from '@angular/router';
import { Cars } from './cars/cars';
import { CarDetails } from './cars/car-details/car-details';
import { Register } from './auth/register/register';
import { Login } from './auth/login/login';
import { NewCar } from './cars/new-car/new-car';
import { NotifierModule } from "angular-notifier";
import { ManageCommentsComponent } from './manage-comments/manage-comments.component';
import { MessageComponent } from './message/message.component';
import { GradesAndCommentsComponent } from './grades-and-comments/grades-and-comments.component';
import { Statistics } from './statistics/statistics';
import { RentingRequests } from './renting-requests/renting-requests';
import { StatisticsService } from './services/statistics/statistics.service';
import { RentingRequestsService } from './services/renting-requests/renting-requests.service';


@NgModule({
  declarations: [
    AppComponent,
    Register,
    Login, 
    Header,
    HomePage,
    NewCar,
    Cars,
    CarDetails,
    ManageCommentsComponent,
    MessageComponent,
    GradesAndCommentsComponent,
    Statistics,
    RentingRequests
  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatDialogModule,
    NotifierModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AuthService,
    ToastrService,
    CarsService,
    RentingRequestsService,
    StatisticsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

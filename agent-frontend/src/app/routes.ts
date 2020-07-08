import { HomePage } from './home-page/home-page';
import { Routes } from '@angular/router';
import { Cars } from './cars/cars';
import { CarDetails } from './cars/car-details/car-details';
import { Register } from './auth/register/register';
import { Login } from './auth/login/login';
import { NewCar } from './cars/new-car/new-car';
import {ManageCommentsComponent} from './manage-comments/manage-comments.component'
import {MessageComponent} from './message/message.component'
import { Statistics } from './statistics/statistics';
import { RentingRequests } from './renting-requests/renting-requests';
import { MapComponent } from './map/map.component';

export const appRoutes:Routes = [
    {path: "cars", component: Cars},
    {path: "car/:id", component: CarDetails},
    {path: "map/:id", component: MapComponent},
    {path: "message", component: MessageComponent},
    {path: "new", component: NewCar},
    {path: "register", component: Register},
    {path: "login", component: Login},
    {path: "manage-comments", component: ManageCommentsComponent},
    {path: "statistics", component: Statistics},
    {path: "renting-requests", component: RentingRequests},
    {path: "", component: HomePage}
]
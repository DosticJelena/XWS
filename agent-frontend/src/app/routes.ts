import { HomePage } from './home-page/home-page';
import { Routes } from '@angular/router';
import { Cars } from './cars/cars';
import { CarDetails } from './cars/car-details/car-details';
import { Register } from './auth/register/register';
import { Login } from './auth/login/login';
import { NewCar } from './cars/new-car/new-car';
import {ManageCommentsComponent} from './manage-comments/manage-comments.component'
import {MessageComponent} from './message/message.component'

export const appRoutes:Routes = [
    {path: "cars", component: Cars},
    {path: "car/:id", component: CarDetails},
    {path: "message", component: MessageComponent},
    {path: "new", component: NewCar},
    {path: "register", component: Register},
    {path: "login", component: Login},
    {path: "manage-comments", component: ManageCommentsComponent},
    {path: "", component: HomePage}
]
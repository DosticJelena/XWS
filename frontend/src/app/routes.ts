import { HomePage } from './home-page/home-page';
import { Routes } from '@angular/router';
import { Cars } from './cars/cars';
import { CarDetails } from './cars/car-details/car-details';
import { Register } from './auth/register/register';
import { Login } from './auth/login/login';
import { ShooppingCart } from './shopping-cart/shopping-cart';

export const appRoutes:Routes = [
    {path: "cars", component: Cars},
    {path: "car/:id", component: CarDetails},
    {path: "register", component: Register},
    {path: "login", component: Login},
    {path: "cart", component: ShooppingCart},
    {path: "", component: HomePage}
]
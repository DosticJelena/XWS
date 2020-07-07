import { HomePage } from './home-page/home-page';
import { Routes } from '@angular/router';
import { Cars } from './cars/cars';
import { CarDetails } from './cars/car-details/car-details';
import { Register } from './auth/register/register';
import { Login } from './auth/login/login';
import { ShooppingCart } from './shopping-cart/shopping-cart';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { NewCar } from './cars/new-car/new-car';
import { CodebookComponent } from './codebook/codebook.component';
import {ManageCommentsComponent} from './manage-comments/manage-comments.component'
import {MessageComponent} from './message/message.component'
import { Profile } from './profile/profile';
import { Inbox } from './inbox/inbox';

export const appRoutes:Routes = [
    {path: "cars", component: Cars},
    {path: "car/:id", component: CarDetails},
    {path: "reservations", component: MessageComponent},
    {path: "new", component: NewCar},
    {path: "register", component: Register},
    {path: "login", component: Login},
    {path: "manage-users", component: ManageUsersComponent},
    {path: "cart", component: ShooppingCart},
    {path: "codebook", component: CodebookComponent},
    {path: "manage-comments", component: ManageCommentsComponent},
    {path: "profile", component: Profile},
    {path: "inbox", component: Inbox},
    {path: "", component: HomePage}
]
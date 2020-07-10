import { Injectable } from '@angular/core'
import { IUser } from './user.model';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable()
export class AuthService {

    currentUser: IUser;
    loggedUser = JSON.parse(localStorage.getItem("loggedUser"));

    constructor(private router: Router, private http: HttpClient) {

    }

    isAuthenticated() {
        return !!JSON.parse(localStorage.getItem("loggedUser"));
    }

    loginUser(formValues) {
        event.preventDefault();

        var loginInfo = {
            username: formValues.userEmail,
            password: formValues.userPassword
        }
        return this.http.post(`http://localhost:8090/auth/login`, loginInfo)
            .pipe(
                map((res: any) => {
                    const data = res;
                    return data;
                }),
                catchError((err: any) => {
                    console.log(err);
                    return throwError(err);
                })
            )
    }

    registerUser(formValues) {
        event.preventDefault();

        var regInfo = {
            username: formValues.username,
            password: formValues.password,
            firstName: formValues.firstName,
            lastName: formValues.lastName
        }
        return this.http.post(`http://localhost:8090/auth/register`, regInfo)
            .pipe(
                map((res: any) => {
                    const data = res;
                    return data;
                }),
                catchError((err: any) => {
                    console.log(err);
                    return throwError(err);
                })
            )
    }

    logoutUser() {
        this.loggedUser = undefined;
        localStorage.removeItem("loggedUser");
        localStorage.removeItem("id");
    }

}
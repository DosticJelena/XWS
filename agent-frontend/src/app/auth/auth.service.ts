import { Injectable } from '@angular/core'
import { IUser } from './user.model';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable()
export class AuthService {

    currentUser: IUser;

    constructor(private router: Router, private http: HttpClient) {

    }

    isAuthenticated() {
        return !!this.currentUser;
    }

    loginUser(formValues) {
        event.preventDefault();
        console.log("HTTP zastev za login");
        console.log(formValues);

        //za sad 
        this.currentUser = {
            id: 1,
            firstName: "Privremeni",
            lastName: "Korisnik",
            email: "privremenikorisnik@gmail.com"
        }

        //posle
        /*
        var loginInfo = {
            email: formValues.userEmail,
            password: formValues.userPassword
        }
        var options = { headers: new HttpHeaders({"Content-Type:":"application/json"})};
        this.http.post('/...', loginInfo, options)
        .pipe(tap(response => {
            this.currentUser = <IUser>response;
        }))
        .pipe(catchError(error => {
            
        }))
        */

        this.router.navigate(['/']);
    }

    registerUser(formValues) {
        event.preventDefault();
        console.log("HTTP zastev za registraciju");
        console.log(formValues);

        this.router.navigate(['/']);
    }

    logoutUser() {
        this.currentUser = undefined;

        //server
    }

    getUsers() {
        return this.http.get("http://localhost:8090/auth/users")
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

    changeUserStatus(id: number, status: number) {
        return this.http.post("http://localhost:8090/auth/users/update-user-status", {
            id: id,
            status: status
        })
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
}
import { Component } from '@angular/core'
import { AuthService } from '../auth/auth.service';

@Component({
    selector: 'home-page',
    templateUrl: './home-page.html',
    styleUrls: ['./home-page.css']
})
export class HomePage {

    userEmail: String;
    userPassword: String;

    constructor(public authService: AuthService) {

    }

    loginRequest(formValues: any) {
        this.authService.loginUser(formValues)
            .subscribe(
                (data: any) => {
                    console.log(data);
                    this.authService.loggedUser = data;
                    localStorage.setItem("loggedUser", JSON.stringify(data));
                },
                (error) => alert(error.text));
    }

}
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  userEmail: String;
  userPassword: String;

  constructor(private authService:AuthService, private router: Router){

  }

  loginRequest(formValues: any) {
    this.authService.loginUser(formValues)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.authService.loggedUser = data;
          localStorage.setItem("loggedUser",JSON.stringify(data));
          this.router.navigate(["/"]);
        }, 
        (error) => alert(error.text));
  }

}

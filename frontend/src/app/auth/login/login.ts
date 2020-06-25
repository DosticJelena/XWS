import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'login',
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  userEmail: String;
  userPassword: String;

  constructor(private authService:AuthService){

  }

  ngOnInit() {
    console.log(localStorage);
  }

  loginRequest(formValues:any) {
    this.authService.loginUser(formValues);
  }
}

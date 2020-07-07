import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'register',
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  firstName: String;
  lastName: String;
  username: String;
  password: String;

  constructor(private authService:AuthService, private router:Router){

  }

  registerRequest(formValues:any) {
    this.authService.registerUser(formValues).subscribe(
      (data: any) => {
        console.log(data);
        this.router.navigate(["login"]);
      }, 
      (error) => alert(error.text));;
  }


}
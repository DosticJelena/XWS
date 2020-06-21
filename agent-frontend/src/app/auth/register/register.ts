import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'register',
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  firstName: String;
  lastName: String;

  constructor(private authService:AuthService){

  }

  registerRequest(formValues:any) {
    this.authService.registerUser(formValues);
  }


}
import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'my-header',
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class Header {

  constructor(public authService:AuthService){

  }

  logoutUser() {
    this.authService.logoutUser();
  }
}
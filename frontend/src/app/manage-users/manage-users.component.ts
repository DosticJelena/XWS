import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {
  users = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.authService.getUsers()
      .subscribe(
        (data: any) => {
          console.log('Got registered.');
          console.log(data);
          this.users = Object.assign([], (data));
        }, (error) => alert(error.text)
      );
  }

  clickActivate(id) {
    this.authService.changeUserStatus(id, 0)
      .subscribe(
        (data: any) => {
          console.log('User activated!');
          this.getUsers();
        }, (error) => alert(error.text)
      );
  }

  clickBlock(id) {
    this.authService.changeUserStatus(id, 2)
      .subscribe(
        (data: any) => {
          console.log('User blocked!');
          this.getUsers();
        }, (error) => alert(error.text)
      );
  }

  clickDelete(id) {
    this.authService.changeUserStatus(id, 1)
      .subscribe(
        (data: any) => {
          console.log('User deleted!');
          this.getUsers();
        }, (error) => alert(error.text)
      );
  }
}

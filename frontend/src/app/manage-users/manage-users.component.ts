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

}

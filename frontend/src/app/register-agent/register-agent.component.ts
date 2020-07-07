import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-register-agent',
  templateUrl: './register-agent.component.html',
  styleUrls: ['./register-agent.component.css']
})
export class RegisterAgentComponent implements OnInit {
  username = "";
  firstName = "";
  lastName = "";
  address = "";
  PIB = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  register() {
    this.authService.registerAgent(this.username, this.firstName, this.lastName, this.address, this.PIB)
      .subscribe(
        (data: any) => {
          console.log('Agent registered!');
        }, (error) => alert(error.text)
      );
  }

}

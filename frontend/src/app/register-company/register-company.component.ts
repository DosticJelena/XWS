import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.css']
})
export class RegisterCompanyComponent implements OnInit {
  username = "";
  companyName = "";
  address = "";
  PIB = "";

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  register() {
    this.authService.registerCompany(this.username, this.companyName, this.address, this.PIB)
      .subscribe(
        (data: any) => {
          console.log('Company registered!');
        }, (error) => alert(error.text)
      );
  }

}

import { Component } from '@angular/core'
import { AuthService } from '../auth/auth.service';
import { CarsService } from '../cars/cars.service';
import { MessageService } from '../services/message/message.service';

@Component({
    selector: 'profile',
    templateUrl: './profile.html',
    styleUrls: ['./profile.css']
})
export class Profile {

    loggedUser = JSON.parse(localStorage.getItem("loggedUser"));
    ownerCars: [];
    requests: [];
    visible = -1;

    constructor(public authService: AuthService, public carsService: CarsService, private msgService: MessageService) {

    }

    ngOnInit() {
        this.carsService.getOwnerCars().subscribe(
            (data: any) => {
              this.ownerCars = data;
            }, (error) => alert(error.text)
          );

          this.getOwnerRequests();
    }

    getOwnerRequests() {
      this.msgService.getOwnerRequests()
        .subscribe(
          (data: any) => {   
            console.log(data);       
            this.requests = Object.assign([], (data));
          }, (error) => alert(error.text)
        );
    }

    showRequests(carId:number) {
      if (this.visible === carId){
        this.visible = -1;
      } else {
        this.visible = carId;
      }
    }

}
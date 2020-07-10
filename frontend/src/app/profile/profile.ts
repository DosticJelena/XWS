import { Component } from '@angular/core'
import { AuthService } from '../auth/auth.service';
import { CarsService } from '../cars/cars.service';
import { MessageService } from '../services/message/message.service';
import { RentingRequestService } from '../services/renting-request/renting-request.service';

@Component({
    selector: 'profile',
    templateUrl: './profile.html',
    styleUrls: ['./profile.css']
})
export class Profile {

    loggedUser = JSON.parse(localStorage.getItem("loggedUser"));
    ownerCars: [];
    requests= [];
    visible = -1;
    users = [];
    map = new Map<number, string>();

    constructor(public authService: AuthService, public carsService: CarsService, private msgService: MessageService,
      private rrService: RentingRequestService) {

    }

    ngOnInit() {
        this.carsService.getOwnerCars().subscribe(
            (data: any) => {
              this.ownerCars = data;
            }, (error) => alert(error.text)
          );

          this.getOwnerRequests();
          this.getUsernames();
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

    approve(reqId:number) {
      console.log("Approved");
      this.rrService.approve(reqId)
        .subscribe(
          (data: any) => {
            console.log(data);
            let index = -1;
            this.requests.forEach(r => {
              if (r.id === data.id){
                  index = this.requests.indexOf(r);
              }
            });
            if (index !== -1){
              this.requests.splice(index,1);
            }
          }, (error) => alert(error.text)
        );
    }

    decline(reqId:number) {
      console.log("Declined");
      this.rrService.decline(reqId)
        .subscribe(
          (data: any) => {
            console.log(data);
            let index = -1;
            this.requests.forEach(r => {
              if (r.id === data.id){
                  index = this.requests.indexOf(r);
              }
            });
            if (index !== -1){
              this.requests.splice(index,1);
            }
          }, (error) => alert(error.text)
        );
    }

    getUsernames() {
      this.authService.getUsers()
        .subscribe(
          (data: any) => {
            this.users = Object.assign([], (data));
            this.setUserName();
          }, (error) => alert(error.text)
        );
    }

    setUserName() {
      this.users.forEach(user => {
        console.log(user)
        this.map.set(user.id, user.username);
      });
      console.log(this.map);
    }

}
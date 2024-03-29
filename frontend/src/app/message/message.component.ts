import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message/message.service'
import { AuthService } from '../auth/auth.service'
import { GradeAndCommentService } from '../services/grade-and-comment/grade-and-comment.service'
import { CarsService } from '../cars/cars.service';
import { RentingRequestService } from '../services/renting-request/renting-request.service';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  reservations = [];
  usernames = [];
  vehicles = [];
  requests : [];
  map = new Map<number, string>();
  vehicleMap = new Map<number, string>();
  counter = 0;
  grade = 0;
  selected = "pending";
  id : number;
  loggedUser = JSON.parse(localStorage.getItem("loggedUser"));

  constructor(private MessageService: MessageService,
    private AuthService: AuthService,
    private GradeAndCommentService: GradeAndCommentService,
    private carsService: CarsService,
    private rentingRequestService : RentingRequestService) { }

  ngOnInit(): void {
    this.reloadReservations();
    this.getUsernames();
    this.getVehicles();
    this.id = JSON.parse(localStorage.getItem("loggedUser")).id;

  }

  onItemChange(carId: number, value) {
    console.log(" Value is : ", value);
    this.grade = value;
    this.GradeAndCommentService.makeGrade(carId, JSON.parse(localStorage.getItem("loggedUser")).id, value)
      .subscribe(
        (data: any) => {
          console.log(data);
        }, (error) => alert(error.text)
      );
  }

  confrim(car: number, receiver: number, i) {
    console.log()
    this.GradeAndCommentService.postComment(car, this.loggedUser.id, (<HTMLInputElement>document.getElementById("r" + i)).value)
      .subscribe(
        (data: any) => {
          console.log(data);
          (<HTMLInputElement>document.getElementById("r" + i)).value = "";
        }, (error) => alert(error.text)
      );
  }

  getUsernames() {
    this.AuthService.getUsers()
      .subscribe(
        (data: any) => {
          this.usernames = Object.assign([], (data));
          this.setUserName();
        }, (error) => alert(error.text)
      );
  }

  getVehicles() {
    this.carsService.getCars()
      .subscribe(
        (data: any) => {
          this.vehicles = Object.assign([], (data));
          this.setCar();
        }, (error) => alert(error.text)
      );
  }

  reloadReservations() {
    this.MessageService.reload(JSON.parse(localStorage.getItem("loggedUser")).id)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.reservations = Object.assign([], (data));
        }, (error) => alert(error.text)
      );
  }

  sendMess(receiver, i) {
    this.MessageService.sendMess(receiver, JSON.parse(localStorage.getItem("loggedUser")).id, (<HTMLInputElement>document.getElementById(i)).value)
      .subscribe(
        (data: any) => {
          console.log(data);
          (<HTMLInputElement>document.getElementById(i)).value = "";
        }, (error) => alert(error.text)
      );
  }

  setUserName() {
    this.usernames.forEach(user => {
      console.log(user)
      this.map.set(user.id, user.username);
    });
    console.log(this.map);
  }

  setCar() {
    this.vehicles.forEach(vehicle => {
      this.vehicleMap.set(vehicle.id, vehicle.brand + " | " + vehicle.model);
    });
    console.log(this.vehicleMap);
  }

  changeTable() {
    if (this.counter === 0) {
      (<HTMLInputElement>document.getElementById("PENDING")).style.display = "none";
      (<HTMLInputElement>document.getElementById("RESERVED")).style.display = "block";

    }
  }
  
  changeSelected(info: number) {
    switch (info) {
      case 0: 
        this.selected = 'pending'; break;
      case 1:
        this.selected = 'paid'; break;
      case 2:
        this.selected = 'declined'; break;
      case 3:
        this.selected = 'finished'; break;
    }
  }
  cancle(requestId : number) {
    this.rentingRequestService.cancle(requestId).subscribe(
      (data) => {
        console.log(data);
        this.reloadReservations();
      },(error) => {
        console.log(error);
      }
    )
  }
}

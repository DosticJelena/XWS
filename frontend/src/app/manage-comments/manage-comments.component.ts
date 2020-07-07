import { Component, OnInit } from '@angular/core';
import { CommentsService } from '../services/comment/comment.service'
import { AuthService } from '../auth/auth.service';
import { CarsService } from '../cars/cars.service';

@Component({
  selector: 'app-manage-comments',
  templateUrl: './manage-comments.component.html',
  styleUrls: ['./manage-comments.component.css']
})
export class ManageCommentsComponent implements OnInit {

  comments = [];
  users = [];
  vehicles = [];
  userMap = new Map<number, string>();
  vehicleMap = new Map<number, string>();

  constructor(private CommentsService: CommentsService,
    private AuthService: AuthService,
    private carsService: CarsService) { }

  ngOnInit(): void {
    this.reloadComments()
    this.getUsernames();
    this.getVehicles();
  }

  reloadComments() {
    this.CommentsService.getPendingComments()
      .subscribe(
        (data: any) => {
          console.log(data);
          this.comments = Object.assign([], (data));
        }, (error) => alert(error.text)
      );
  }

  approve(id) {
    this.CommentsService.approve(id)
      .subscribe(
        (data: any) => {
          this.reloadComments();
        }, (error) => alert(error.text)
      );
  }

  reject(id) {
    this.CommentsService.reject(id)
      .subscribe(
        (data: any) => {
          this.reloadComments();
        }, (error) => alert(error.text)
      );
  }

  getUsernames() {
    this.AuthService.getUsers()
      .subscribe(
        (data: any) => {
          this.users = Object.assign([], (data));
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

  setUserName() {
    this.users.forEach(user => {
      console.log(user)
      this.userMap.set(user.id, user.username);
    });
  }

  setCar() {
    this.vehicles.forEach(vehicle => {
      this.vehicleMap.set(vehicle.id, vehicle.brand + " | " + vehicle.model);
    });
    console.log(this.vehicleMap);
  }

}

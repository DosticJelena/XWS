import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart/cart.service';
import { CarsService } from '../cars/cars.service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'shopping-cart',
  templateUrl: './shopping-cart.html',
  styleUrls: ['./shopping-cart.css']
})
export class ShooppingCart {

  cars: any;
  userId: any;
  bundle : any = false;

  startDate: any;
  endDate: any;
  timeFrom: any;
  timeTo: any;
  minDate: any;
  minDateSecond: any;

  constructor(private route: ActivatedRoute,
    private carsService: CarsService,
    private cartService: CartService,
    private authService: AuthService) {

  }
  ngOnInit() {
    this.userId = JSON.parse(localStorage.getItem("loggedUser")).id;
    this.getAllVehicles();

  }

  getAllVehicles() {
    this.cartService.getCarsInCart(this.userId).subscribe(
      (data: any) => {
        this.authService.getUsers().subscribe(
          (data1: any) => {
            this.cars = data;
            this.cars = this.cars.map((car) => {
              return {
                ...car,
                owner_name: data1.filter((user) => user.id === car.owner_id)[0].username
              }
            })
          })
      },
      (error: any) => {
      }
    )

  }
  reserveCars() {
    if (this.startDate.month > this.endDate.month) {
      alert("End day cannot be before start day!");
      return;
    } else if (this.startDate.day > this.endDate.day && this.startDate.month == this.endDate.month) {
      alert("End day cannot be before start day!");
      return;
    }
    if (this.startDate.day == this.endDate.day && this.startDate.month == this.endDate.month) {
      alert("You have to reserve car for at least one day");
      return;
    }

    var startMonth; var startDay; var endMonth; var endDay;
    var startHour; var startMin; var endHour; var endMin;

    if (this.startDate.day < 10) {
      startDay = "0" + this.startDate.day;
    } else {
      startDay = this.startDate.day;
    }
    if (this.startDate.month < 10) {
      startMonth = "0" + this.startDate.month;
    } else {
      startMonth = this.startDate.month;
    }

    if (this.endDate.month < 10) {
      endMonth = "0" + this.endDate.month;
    } else {
      endMonth = this.endDate.month;
    }

    if (this.endDate.day < 10) {
      endDay = "0" + this.endDate.day;
    } else {
      endDay = this.endDate.day;
    }

    var start = this.startDate.year + "-" + startMonth + "-" + startDay + " 00:00";
    var end = this.endDate.year + "-" + endMonth + "-" + endDay + " 00:00";
    console.log(start);
    console.log(end);
    if(this.bundle === true) {
      this.cartService.createBundlePerOwner(this.userId,start,end).subscribe(
        (data : any) => {
          console.log(data);
          //location.reload();
          if(data.statusCodeValue === 400) {
            alert(data.body);
          }
          else {
            location.replace("cars");
          }
        },
        (err) => {
          console.log(err);
        }
      )
    }else {
      this.cartService.createRequestPerVehicle(this.userId,start,end).subscribe(
        (data : any) => {
          console.log(data);
          //location.reload();
          if(data.statusCodeValue === 400) {
            alert(data.body);
          }
          else {
            location.replace("cars");
          }
        },
        (err) => {
          alert(err.body);
        }
      )
    }
  }
  onChange(isChecked: boolean) {
    

    if (isChecked) {
      this.bundle = true;
    } else {
      this.bundle = false;
    }
  }



}
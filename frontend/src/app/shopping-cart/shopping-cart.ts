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
            }
            )
            console.log(this.cars);

          })




      },
      (error: any) => {
      }
    )
  }



}
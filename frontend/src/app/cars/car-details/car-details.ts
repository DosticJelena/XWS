import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarsService } from '../cars.service';
import { CartService } from 'src/app/services/cart/cart.service';
import { CloseScrollStrategy } from '@angular/cdk/overlay';

@Component({
    selector: 'car-details',
    templateUrl: './car-details.html',
    styleUrls: ['./car-details.css']
})
export class CarDetails {
    car:any;
    images:any;

    constructor(private route:ActivatedRoute, 
      private carsService:CarsService,
      private cartService : CartService){

    }

    ngOnInit(){
        this.carsService.getCar(this.route.snapshot.params['id']).subscribe(
            (data: any) => {
              this.car = data;
            }, (error) => alert(error.text)
          );
        this.carsService.getImages(this.route.snapshot.params['id']).subscribe(
          (data: any) => {
            this.images = data;
          }, (error) => alert(error.text)
        );
    }
    addToCart() {
      this.cartService.addToCart(JSON.parse(localStorage.getItem('loggedUser')).id,this.car.id).subscribe(
        (data : any) => {
          console.log(data);

        },(error : any) => {
          console.log(error);
        })

    }
}
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart/cart.service';
import { CarsService } from '../cars/cars.service';

@Component({
  selector: 'shopping-cart',
  templateUrl: './shopping-cart.html',
  styleUrls: ['./shopping-cart.css']
})
export class ShooppingCart {

  cars: any;

  constructor(private route: ActivatedRoute,
    private carsService: CarsService,
    private cartService: CartService) {

  }
  ngOnInit(){
    
}



}
import { Component, OnInit } from '@angular/core';
import { CarsService } from '../cars/cars.service';
import { DiscountService } from '../services/discount/discount.service';

@Component({
  selector: 'app-add-discount',
  templateUrl: './add-discount.component.html',
  styleUrls: ['./add-discount.component.css']
})
export class AddDiscountComponent implements OnInit {

  userId : any;
  vehicles : any;
  discounts : any;
  vehicle: any;
  discount : any;
  constructor(private carService : CarsService,
    private discountService :DiscountService) { }

  ngOnInit(): void {
    this.userId = JSON.parse(localStorage.getItem("loggedUser")).id;
    this.carService.getOwnerCars().subscribe(
      (data : any) => {
        console.log(data);
        this.vehicles = data;
      }
    )
    this.discountService.getAllDiscounts().subscribe(
      (data : any) => {
        console.log(data);
        this.discounts = data;
      }
    )
  }
  changeSelectedVehicle(filterVal: any) {
    this.vehicle = filterVal;
  }
  changeSelectedDiscount(filterVal: any) {
    this.discount = filterVal;
  }
  submitNewDiscount() {
    this.discountService.applyDiscountToVehicle(this.vehicle,this.discount).subscribe(
      (data : any) => {
        console.log(data);
      }
    )
  }

}

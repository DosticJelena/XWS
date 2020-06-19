import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarsService } from '../cars.service';
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
      private carsService:CarsService){

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
    
}
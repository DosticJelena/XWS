import { Component, OnInit } from '@angular/core'
import { CarsService } from './cars.service';

@Component({
    selector: 'cars',
    templateUrl: './cars.html',
    styleUrls: ['./cars.css']
})
export class Cars implements OnInit {
    cars:any;
    constructor (private carsService:CarsService) {

    }

    ngOnInit() {
        this.carsService.getCars().subscribe(
            (data: any) => {
              this.cars = data;
            }, (error) => alert(error.text)
          );
        console.log(this.cars);
    }
}
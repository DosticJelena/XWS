import { Component, OnInit } from '@angular/core'
import { CarsService } from './cars.service';

@Component({
    selector: 'cars',
    templateUrl: './cars.html',
    styleUrls: ['./cars.css']
})
export class Cars implements OnInit {
    carsData:any;
    constructor (private carsService:CarsService) {

    }

    ngOnInit() {
        this.carsData = this.carsService.getCars();
    }
}
import { Component, OnInit } from '@angular/core'
import { CarsService } from './cars.service';

@Component({
    selector: 'cars',
    templateUrl: './cars.html',
    styleUrls: ['./cars.css']
})
export class Cars implements OnInit {
    carsData:any = [];

    constructor (private carsService:CarsService) {

    }

    ngOnInit() {
        this.getCarsFromService();
    }

    getCarsFromService() {
        this.carsService.getCars()
        .subscribe(
            (res:any) => {
                console.log(res);
                this.carsData = Object.assign([], res)
            }, (error) => alert(error.text)
        );
    }
}
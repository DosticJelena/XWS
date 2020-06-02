import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarsService } from '../cars.service';

@Component({
    selector: 'car-details',
    templateUrl: './car-details.html',
    styleUrls: ['./car-details.css']
})
export class CarDetails {
    car:any

    constructor(private route:ActivatedRoute, private carsService:CarsService){

    }

    ngOnInit(){
        this.getCarFromService();
    }

    getCarFromService(){
        this.carsService.getCar(this.route.snapshot.params['id'])
        .subscribe(
            (res:any) => {
                console.log(res);
                this.car = res
            }, (error) => alert(error.text)
        );
    }
}
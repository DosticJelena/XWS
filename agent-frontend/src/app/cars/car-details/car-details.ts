import { Component } from '@angular/core';
import { ActivatedRoute, RouteConfigLoadEnd, Router } from '@angular/router';
import { CarsService } from '../cars.service';

@Component({
    selector: 'car-details',
    templateUrl: './car-details.html',
    styleUrls: ['./car-details.css']
})
export class CarDetails {
    car:any;
    images:any;
    mathPath= "[/map/"+this.route.snapshot.params['id'] + "]";

    constructor(private route:ActivatedRoute, 
      private carsService:CarsService, private router:Router){

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

    routeMap() {
      this.router.navigate(['/map/' + this.route.snapshot.params['id'] ]);
    }    
}
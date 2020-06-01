import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'filter-buttons',
    templateUrl: './filter-buttons.html',
    styleUrls: ['./filter-buttons.css']
})
export class FilterButtons {
    showAdditional: any = false;
    location:any;
    startDate:any;
    endDate:any;

    brand:any;
    model:any;
    fuelType:any;
    transmission:any;
    vehicleType:any;
    price:any;
    distance: any;
    CDWStatus: any;
    childrenSeats:any;

    showHideAdditional() {
        this.showAdditional = !this.showAdditional;
    }

    filterCars() {
        let url = "http://localhost:8080/vehicle/search?location=ns&startDate&endDate";
        let additional =  "&brand&model&fuel_type&transmission&type&price&distance&CDWStatus&childrenSears";

        //http za micketa
    }

}
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';



@Component({
    selector: 'filter-buttons',
    templateUrl: './filter-buttons.html',
    styleUrls: ['./filter-buttons.css']
})
export class FilterButtons {
    constructor(private calendar: NgbCalendar,
        public formatter: NgbDateParserFormatter) { }

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
        console.log("Lokacija:"+this.location + this.startDate.year + this.endDate);
        let url = "http://localhost:8080/vehicle/vehicle/search?location=NS&startDate=&endDate&brand&model&fuel_type&transmission&type&price&distance&CDWStatus&childrenSears";

    }
    

}
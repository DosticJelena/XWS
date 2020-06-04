import { Component, OnInit } from '@angular/core'
import { CarsService } from '../cars.service';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'new-car',
    templateUrl: './new-car.html',
    styleUrls: ['./new-car.css']
})
export class NewCar implements OnInit {
    location: any;
    brand: any;
    model: any;

    fuelType: any;
    transmission: any;
    vehicleType: any;

    price: any;
    additionalPrice: any;
    distance: any;
    distancePerRent: any = 0;
    distancePerRentStatus: any = false;
    CDWStatus: any = "No";
    childrenSeats: any = 0;

    constructor(private carsService: CarsService) {

    }

    ngOnInit() {

    }

    newVehicleRequest(formValues: any) {
        let DPRstatus: any;
        // validacija...
        this.location = formValues.location;
        this.brand = formValues.brand;
        this.model = formValues.model;
        this.distance = formValues.distance;
        this.distancePerRentStatus ? this.distancePerRent = formValues.distancePerRent : this.distancePerRent = 0;
        this.distancePerRentStatus ? DPRstatus = "Yes" : DPRstatus = "No";
        this.price = formValues.price;
        this.additionalPrice = formValues.additionalPrice;
        this.childrenSeats = formValues.childrenSeats;
        // srediti
        let newValues = {
            brand: this.brand,
            model: this.model,
            location: this.location,
            fuelType: this.fuelType,
            transmission: this.transmission,
            vehicleType: this.vehicleType,
            price: this.price,
            distance: this.distance,
            DPRstatus: DPRstatus,
            additionalPrice: this.additionalPrice,
            childrenSeats: this.childrenSeats,
            CDWStatus: this.CDWStatus
        }

        this.carsService.addNewVehicle(newValues);
    }

    setDprStatus() {
        this.distancePerRentStatus = !this.distancePerRentStatus;
    }

    setLocation(location: String) {
        this.location = location;
    }

    changeCDW(yesNo: String) {
        this.CDWStatus = yesNo;
    }
}
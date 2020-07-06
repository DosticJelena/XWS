import { Component, OnInit } from '@angular/core'
import { CarsService } from '../cars.service';
import { Router } from '@angular/router';
import { NotifierService } from "angular-notifier";

@Component({
    selector: 'new-car',
    templateUrl: './new-car.html',
    styleUrls: ['./new-car.css']
})
export class NewCar implements OnInit {
    showInput: any = false;
    imageURL: any = "";

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
    images: any = [];

    constructor(private carsService: CarsService, private router: Router, private notifierService: NotifierService) {

    }

    ngOnInit() {

    }

    newVehicleRequest(formValues: any) {
        let DPRstatus: any;
        // validacija...
        this.location = formValues.location;
        this.transmission = formValues.transmission;
        this.fuelType = formValues.fuelType;
        this.vehicleType = formValues.vehicleType;
        this.brand = formValues.brand;
        this.model = formValues.model;
        this.distance = formValues.distance;
        this.distancePerRentStatus ? this.distancePerRent = formValues.distancePerRent : this.distancePerRent = 0;
        this.distancePerRentStatus ? DPRstatus = "Yes" : DPRstatus = "No";
        this.price = formValues.price;
        this.additionalPrice = formValues.additionalPrice;
        this.childrenSeats = formValues.childrenSeats;
        
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
            CDWStatus: this.CDWStatus,
            pictures: this.images,
            owner_id: JSON.parse(localStorage.getItem("loggedUser")).id
        }

        this.carsService.addNewVehicle(newValues)
            .subscribe(
                (data: any) => {
                    console.log(data);
                    //this.notifierService.notify("Success!","New Vehicle added.");
                    this.router.navigate(['car/' + data.id]);
                }, (error) => {
                    console.log(error.text);
                    this.notifierService.notify("Error!", error.text);
                }
            );;
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

    showInputField() {
        this.showInput = true;
    }

    hideInputField() {
        this.showInput = false;
    }

    onKey(event) {
        this.imageURL = event.target.value;
    }

    addImage() {
        this.images.push(this.imageURL);
        this.imageURL = "";
        this.hideInputField();
    }

}
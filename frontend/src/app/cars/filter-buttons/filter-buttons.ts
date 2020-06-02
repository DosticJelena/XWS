import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { CarsService } from '../cars.service';



@Component({
    selector: 'filter-buttons',
    templateUrl: './filter-buttons.html',
    styleUrls: ['./filter-buttons.css']
})
export class FilterButtons {
    constructor(private calendar: NgbCalendar,
        public formatter: NgbDateParserFormatter, private carsService:CarsService) { }

    showAdditional: any = false;
    location:any;
    startDate:any;
    endDate:any;
    timeFrom:any;
    timeTo:any;

    brand:any;
    model:any;
    fuelType:any;
    transmission:any;
    vehicleType:any;
    price:any;
    distance: any;
    CDWStatus: any;
    childrenSeats:any;
        
    hoveredDate: NgbDate;

    today: NgbDate;
    cars : any;
    minDate:any;
    minDateSecond : any;
    ngOnInit() {
        this.today = this.calendar.getToday();
        this.cars = this.carsService.getCars();
        this.location = "Choose location";
        this.minDate = new NgbDate(this.today.year,this.today.month,this.today.day+2);
        this.minDateSecond = new NgbDate(this.today.year,this.today.month,this.today.day+2);
    }
    showHideAdditional() {
        this.showAdditional = !this.showAdditional;
    }
   
    filterCars() {
        var start = this.startDate.year+"-"+this.startDate.month+"-"+this.startDate.day +" " + this.timeFrom.hour+":"+this.timeFrom.minute;
        var end = this.endDate.year+"-"+this.endDate.month+"-"+this.endDate.day +" " + this.timeTo.hour+":"+this.timeTo.minute;
        this.carsService.filter(this.location,start,end);
        
    }
    setLocation(location:String){
       this.location = location;
    }
    
  
    

}
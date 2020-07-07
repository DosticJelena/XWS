import { Component, OnInit } from '@angular/core'
import { CarsService } from './cars.service';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'cars',
    templateUrl: './cars.html',
    styleUrls: ['./cars.css']
})
export class Cars implements OnInit {
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
  priceFrom=0;
  priceTo=0;
  distanceFrom=0;
  distance=0;
  distanceTo=0;
  CDWStatus: any;
  childrenSeats=0;
      

  today: NgbDate;
  cars : any;
  minDate:any;
  minDateSecond : any;
    constructor (private carsService:CarsService,private calendar: NgbCalendar,) {

    }
    setCDW(cdw:String){
        this.CDWStatus = cdw;
    }
    setModel(model:String){
      this.model = model;
    }
    setTransmission(transmission:String){
      this.transmission = transmission;
    }
    setVehicleType(vehicleType:String){
      this.vehicleType = vehicleType;
    }
    setBrand(brand:String){
      this.brand = brand;
    }
    setFuelType(fuelType:String){
      this.fuelType = fuelType;
    }
    sortCena(){
      this.cars.sort((a, b) => a.price - b.price);
    }
    sortKm(){
      this.cars.sort((a, b) => a.distance - b.distance);
    }
    ngOnInit() {
       
        this.carsService.getCars().subscribe(
            (data: any) => {
              this.cars = data;
            }, (error) => alert(error.text)
          );
          
        this.today = this.calendar.getToday();
        //this.cars = this.carsService.getCars();
        this.location = "Choose location";
        this.brand = "Brand";
        this.model = "Model";
        this.transmission = "Transmission";
        this.fuelType = "Fuel type";
        this.vehicleType = "Vehicle type";
        this.CDWStatus ="CDW";
        this.minDate = new NgbDate(this.today.year,this.today.month,this.today.day+2);
        this.minDateSecond = new NgbDate(this.today.year,this.today.month,this.today.day+2);
    }
    showHideAdditional() {
        this.showAdditional = !this.showAdditional;
    }
  
    filterCars() {
        if(this.startDate.month>this.endDate.month){
          alert("End day cannot be before start day!");
          return;
        }else if(this.startDate.day>this.endDate.day && this.startDate.month==this.endDate.month ){
          alert("End day cannot be before start day!");
          return;
        }
        if(this.startDate.day==this.endDate.day &&  this.startDate.month==this.endDate.month ){
          alert("You have to reserve car for at least one day");
          return;
        }
        
        var startMonth;var startDay;var endMonth;var endDay;
        var startHour;var startMin;var endHour;var endMin;

        if ( this.timeFrom.hour<10){
          startHour="0"+ this.timeFrom.hour;
        }else{
          startHour= this.timeFrom.hour;
        }

        if (this.timeFrom.minute<10){
          startMin="0"+this.timeFrom.minute;
        }else{
          startMin=this.timeFrom.minute;
        }

        if (  this.timeTo.hour<10){
          endHour="0"+  this.timeTo.hour;
        }else{
          endHour=  this.timeTo.hour;
        }

        if (this.timeTo.minute<10){
          endMin="0"+this.timeTo.minute;
        }else{
          endMin=this.timeTo.minute;
        }

        if (this.startDate.day<10){
          startDay="0"+this.startDate.day;
        }else{
          startDay=this.startDate.day;
        }
        if (this.startDate.month<10){
          startMonth="0"+this.startDate.month;
        }else{
          startMonth=this.startDate.month;
        }

        if (this.endDate.month<10){
          endMonth="0"+this.endDate.month;
        }else{
          endMonth=this.endDate.month;
        }
        
        if (this.endDate.day<10){
          endDay="0"+this.endDate.day;
        }else{
          endDay=this.endDate.day;
        }
            
        var start = this.startDate.year+"-"+startMonth+"-"+startDay +" " + startHour+":"+startMin;
        var end = this.endDate.year+"-"+endMonth+"-"+endDay +" " +endHour+":"+endMin;
        if(this.priceTo===null){
          this.priceTo = -1;
        }
        if(this.priceFrom===null){
          this.priceFrom = -1;
        }
        if(this.distanceFrom===null){
          this.distanceFrom = -1;
        }
        if(this.distanceTo===null){
          this.distanceTo = -1;
        }
        if(this.childrenSeats===null){
          this.childrenSeats = -1;
        }
        this.carsService.filter(this.location,start,end,this.brand,this.model,this.fuelType,this.vehicleType,this.transmission,
          this.priceFrom,this.priceTo,this.distanceFrom,this.distanceTo,this.CDWStatus,this.childrenSeats,this.distance).subscribe(
          (data: any) => {
            this.cars = data;
          }, (error) => alert(error.text)
        );
    }
    setLocation(location:String){
      this.location = location;
    }
}
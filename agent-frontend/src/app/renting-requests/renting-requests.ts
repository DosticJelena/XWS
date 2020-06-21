import { Component } from '@angular/core'
import { RentingRequestsService } from '../services/renting-requests/renting-requests.service';

@Component({
    selector: 'renting-requests',
    templateUrl: './renting-requests.html',
    styleUrls: ['./renting-requests.css']
})
export class RentingRequests {

  rentingRequestVehicle:any;
  rrVehicleList: []
  distance: "";
  addInfo: "";

    constructor(public rentingRequestsService:RentingRequestsService){

    }

    ngOnInit(){

      this.rentingRequestsService.finished(1).subscribe(
        (data: any) => {
          this.rrVehicleList = data;
          console.log(this.rrVehicleList);
        }, (error) => alert(error.text)
      );

    }

    sendReport(carId,i){
      console.log("AA");
      this.rentingRequestsService.report(carId,(<HTMLInputElement>document.getElementById(i)).value, 
      (<HTMLInputElement>document.getElementById("r"+i)).value).subscribe(
        (data: any) => {
          this.rentingRequestVehicle = data;
          
          this.rentingRequestsService.finished(1).subscribe(
            (data: any) => {
              this.rrVehicleList = data;
              console.log(this.rrVehicleList);
            }, (error) => alert(error.text)
          );
          console.log(data);
        }, (error) => alert(error.text)
      );
    }


}
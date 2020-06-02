import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class CarsService {

    constructor(private http: HttpClient) { }
    cars : any;

    getCars() {
        return this.http.get(`http://localhost:8080/vehicle/vehicles`)
        .pipe(
          map((response: any) => {
            const cars_data = response;
            console.log(cars_data);
            return cars_data;
          }),
          catchError((err: any) => {
            return throwError(err);
          })
        )
    }

    getCar(id:number) {
        return this.http.get(`http://localhost:8080/vehicle/vehicles/`+id)
        .pipe(
          map((response: any) => {
            const cars_data = response;
            console.log("EVO"+cars_data);
            return cars_data;
          }),
          catchError((err: any) => {
            console.log(err);
            return throwError(err);
          })
        )
    }
    searchPatients(ime:string,prezime:string,jmbg:string) {
        let url = "http://localhost:8080/vehicle/vehicle/search?location=NS&startDate=&endDate&brand&model&fuel_type&transmission&type&price&distance&CDWStatus&childrenSears";

     }
}


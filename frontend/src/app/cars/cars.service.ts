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
            this.cars = response.data;
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
            return cars_data;
          }),
          catchError((err: any) => {
            console.log(err);
            return throwError(err);
          })
        )
    }
    getImages(id:number) {
      return this.http.get(`http://localhost:8080/vehicle/pictures/`+id)
      .pipe(
        map((response: any) => {
          const images = response;
          return images;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }
    filter(location:string,startDate:string,endDate:string) {
      
        let url = "http://localhost:8080/vehicle/vehicles/search?location="+location+
        "&startDate="+startDate+"&endDate="+endDate+"&&brand&model&fuel_type&transmission&type&price=0&distance=0&minPrice=0&maxPrice=0&CDWStatus&childrenSeats=1";
        console.log(url);
        return this.http.get(url)
        .pipe(
          map((response: any) => {
            const cars_data = response;
            return cars_data;
          }),
          catchError((err: any) => {
            return throwError(err);
          })
        )
     }
}


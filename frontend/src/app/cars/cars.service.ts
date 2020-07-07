import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class CarsService {

  constructor(private http: HttpClient) { }
  cars: any;

  getCars() {
    return this.http.get(`http://localhost:8080/vehicle/`)
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

  getOwnerCars() {
    return this.http.get(`http://localhost:8080/vehicle/owner/` + JSON.parse(localStorage.getItem("loggedUser")).id)
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

  getCar(id: number) {
    return this.http.get(`http://localhost:8080/vehicle/` + id)
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

  getImages(id: number) {
    return this.http.get(`http://localhost:8080/vehicle/pictures/` + id)
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

  getAllImages() {
    return this.http.get(`http://localhost:8080/vehicle/pictures`)
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

  filter(location: string, startDate: string, endDate: string,brand: string,model: string,fuelType: string
    ,vehicleType: string,transmission: string,priceFrom:number,priceTo: number,
    distanceFrom: number,distanceTo: number,CDWStatus:any,childrenSeats:number,distance:number) {
    let url = "http://localhost:8080/vehicle/search?location=" + location +
      "&startDate=" + startDate + "&endDate=" + endDate + 
      "&brand="+brand+"&model="+model+"&fuel_type="+fuelType+"&transmission="+transmission+
      "&type="+vehicleType+"&minDistance="+distanceFrom+"&maxDistance="+distanceTo+"&minPrice="+priceFrom+"&maxPrice="+priceTo+
      "&CDWStatus="+CDWStatus+"&childrenSeats="+childrenSeats;
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

  addNewVehicle(values: any) {
    console.log(values);
    return this.http.post("http://localhost:8080/vehicle/new", {
      owner_id: values.owner_id,
      brand: values.brand,
      model: values.model,
      location: values.location,
      fuel_type: values.fuelType,
      transmission: values.transmission,
      vehicle_type: values.vehicleType,
      price: values.price,
      distance: values.distance,
      distancePerRentStatus: values.DPRstatus,
      additionalPricePerKm: values.additionalPrice,
      childrenSeats: values.childrenSeats,
      cdwstatus: values.CDWStatus,
      pictures: values.pictures
    })
      .pipe(
        map((res: any) => {
          const data = res;
          return data;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError(err);
        })
      )
  }

}


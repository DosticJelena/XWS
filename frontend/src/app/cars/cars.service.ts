import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class CarsService {

    constructor(private http:HttpClient){

    }

    getCars() {
        console.log("getCars")
        return this.http.get('http://localhost:8080/vehicle/vehicles')
        .pipe(
            map((res: any) => {
              return res;
            }),
            catchError((err: any) => {
              console.log(err);
              return throwError(err);
            })
          )
    }

    getCar(id:number) {
        console.log("getCar")
        return this.http.get('http://localhost:8080/vehicle/vehicles/' + id)
        .pipe(
            map((res: any) => {
              return res;
            }),
            catchError((err: any) => {
              console.log(err);
              return throwError(err);
            })
          )
    }
}

const cars_data = [
    {
        id: 1,
        makes: "Mazda",
        model: "CX-3",
        year: "2019"
    },
    {
        id: 2,
        makes: "Nissan",
        model: "Maxima",
        year: "2019"
    },
    {
        id: 3,
        makes: "Kia",
        model: "Optima",
        year: "2019"
    },
    {
        id: 4,
        makes: "Mazda",
        model: "CX-3",
        year: "2018"
    },
    {
        id: 5,
        makes: "Nissan",
        model: "Maxima",
        year: "2018"
    },
    {
        id: 6,
        makes: "Kia",
        model: "Optima",
        year: "2018"
    },
    {
        id: 7,
        makes: "Mazda",
        model: "CX-3",
        year: "2017"
    },
    {
        id: 8,
        makes: "Nissan",
        model: "Maxima",
        year: "2017"
    },
    {
        id: 9,
        makes: "Kia",
        model: "Optima",
        year: "2017"
    }
]
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(private router: Router, private http: HttpClient) { }

    getAllDiscounts() {
        return this.http.get("http://localhost:8080/vehicle/discount")
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
    applyDiscountToVehicle(vehicleId : number,discountId : number) {
      return this.http.post("http://localhost:8080/vehicle/discount",{
        vehicleId : vehicleId,
        discountId : discountId
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
  addNewDiscount(amount : number) {
    return this.http.post("http://localhost:8080/vehicle/discount/new",{
      amount : amount
    }).pipe(
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

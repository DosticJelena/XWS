import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from "./../../../environments/environment"

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addToCart(userId : any,vehicleId : any) {
    return this.http.post(`${environment.baseUrl}/cart/vehicle`,{
      userId : userId,
      vehicleId : vehicleId
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

  getCarsInCart(userId : any) {
    return this.http.get(`${environment.baseUrl}/cart/vehicles/${userId}`)
      .pipe(
        map((res : any) => {
          const data = res;
          console.log(data);
          return data;
        }),
        catchError((err : any) => {
          console.log(err);
          return throwError(err);
        })
      )
  }
  
  createRequestPerVehicle(userId : any,startDate : any,endDate : any) {
    return this.http.post(`${environment.baseUrl}/cart/rentingRequest/separate`,{
      userId : userId,
      startDate : startDate,
      endDate : endDate
    }).pipe(
      map((res : any) => {
        const data = res;
        return data;
      }),
      catchError((err : any) => {
        console.log(err);
        return throwError(err);
      })
    )
  } 
  createBundlePerOwner(userId : any,startDate : any,endDate : any) {
    return this.http.post(`${environment.baseUrl}/cart/rentingRequest/bundle`,{
      userId : userId,
      startDate : startDate,
      endDate : endDate
    })
    .pipe(
      map((res : any) => {
        const data = res;
        return data;
      }),
      catchError((err : any) => {
        console.log(err);
        return throwError(err);
      })
    )
  } 

}

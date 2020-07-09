import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentingRequestService {

  constructor(private router: Router, private http: HttpClient) { }

  getByUserIdAndStatus(id: number,status : number) {
    return this.http.get(`http://localhost:8080/cart/rentingRequest/4/${status}`)
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

  cancle(requestId : number) {
    return this.http.put(`http://localhost:8080/cart/rentingRequest/cancle/${requestId}`,{})
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

  approve(requestId: number){
    return this.http.post(`http://localhost:8080/cart/rentingRequest/reserver/${requestId}`,{})
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

  decline(requestId: number){
    return this.http.put(`http://localhost:8080/cart/rentingRequest/decline/${requestId}`,{})
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

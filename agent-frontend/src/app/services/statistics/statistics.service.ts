import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  constructor(private router: Router, private http: HttpClient) { }

  mostDistance(id:number) {
    return this.http.get("http://localhost:8090/vehicle/most-distance/"+id)
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

mostComments(id:number) {
  return this.http.get("http://localhost:8090/vehicle/most-comments/"+id)
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

bestGrade(id:number) {
  return this.http.get("http://localhost:8090/vehicle/best-grade/"+id)
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


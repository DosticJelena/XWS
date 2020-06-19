import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GradeAndCommentService {

  constructor(private router: Router, private http: HttpClient) { }
  makeGrade(car: number,senderId: number,value: number ) {
    return this.http.post("http://localhost:8080/grading/grade", {
      carId : car,
      userId: senderId,
      value: value
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
postComment(car: number,senderId: number,text: string ) {
  return this.http.post("http://localhost:8080/grading/comment", {
    carId : car,
    userId: senderId,
    text: text
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

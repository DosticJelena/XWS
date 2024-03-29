import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private router: Router, private http: HttpClient) { }

  getPendingComments() {
    return this.http.get("http://localhost:8090/comment/status?status=PENDING")
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

  reject(id: number) {
    return this.http.put("http://localhost:8090/comment/reject", {
      id: id
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
approve(id:number) {
  return this.http.put("http://localhost:8090/comment/approve", {
    id:id
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
      )}

}

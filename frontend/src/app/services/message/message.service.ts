import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private router: Router, private http: HttpClient) { }
  
  reload(id: number) {
    return this.http.get("http://localhost:8080/cart/rentingRequest/" + id)
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

  getOwnerRequests() {
    return this.http.get(`http://localhost:8080/cart/rentingRequest/owner/` + JSON.parse(localStorage.getItem("loggedUser")).id)
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

  getMessages() {
    return this.http.get(`http://localhost:8080/message/` + JSON.parse(localStorage.getItem("loggedUser")).id)
      .pipe(
        map((response: any) => {
          const data = response;
          return data;
        }),
        catchError((err: any) => {
          return throwError(err);
        })
      )
  }

  sendMess(receiverId: number, senderId: number, content: string) {
    return this.http.post("http://localhost:8080/message/", {
      receiverId: receiverId,
      senderId: senderId,
      content: content
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


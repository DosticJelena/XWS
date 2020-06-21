import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RentingRequestsService {

    additionalInfo: any;
    vid: any;
    rid: any;
    distance: any;

    constructor(private router: Router, private http: HttpClient) { }

    report(vid: number,distance: any, additionalInfo: any) {
        return this.http.put("http://localhost:8090/rentingRequest/report", {
            vid: vid,
            rid: 1,
            distance: distance,
            additionalInfo: additionalInfo
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

    finished(id:number) {
        return this.http.get("http://localhost:8090/rentingRequest/finished/" + id)
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
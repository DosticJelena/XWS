import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodebookService {

  constructor(private http: HttpClient) { }

  getFuelTypes() {
    return this.http.get("http://localhost:8080/vehicle/fuel-type")
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

  getVehicleClasses() {
    return this.http.get("http://localhost:8080/vehicle/vehicle-class")
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

  getVehicleModels() {
    return this.http.get("http://localhost:8080/vehicle/vehicle-model")
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

  newFuelType(fuelTypeName: string) {
    return this.http.post("http://localhost:8080/vehicle/fuel-type/new", {
      fuelTypeName: fuelTypeName
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

  newVehicleClass(vehicleClassName: string) {
    return this.http.post("http://localhost:8080/vehicle/vehicle-class/new", {
      vehicleClassName: vehicleClassName
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

  newVehicleModel(model: string, brand: string) {
    return this.http.post("http://localhost:8080/vehicle/vehicle-model/new", {
      model: model,
      brand: brand
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

  updateFuelType(newName: string, id: number) {
    return this.http.put("http://localhost:8080/vehicle/fuel-type/update", {
      newName: newName,
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

  updateVehicleClass(newName: string, id: number) {
    return this.http.put("http://localhost:8080/vehicle/vehicle-class/update", {
      newName: newName,
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

  updateVehicleModel(newModel: string, newBrand: string, id: number) {
    return this.http.put("http://localhost:8080/vehicle/vehicle-model/update", {
      newModel: newModel,
      newBrand: newBrand,
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

  deleteFuelType(id: number) {
    return this.http.post("http://localhost:8080/vehicle/fuel-type/delete", {
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

  deleteVehicleClass(id: number) {
    return this.http.post("http://localhost:8080/vehicle/vehicle-class/delete", {
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

  deleteVehicleModel(id: number) {
    return this.http.post("http://localhost:8080/vehicle/vehicle-model/delete", {
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
}

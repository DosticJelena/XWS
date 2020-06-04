import { Component, OnInit } from '@angular/core';
import { CodebookService } from '../services/codebook.service';

@Component({
  selector: 'app-codebook',
  templateUrl: './codebook.component.html',
  styleUrls: ['./codebook.component.css']
})
export class CodebookComponent implements OnInit {
  fuelTypes = [];
  vehicleClasses = [];
  vehicleModels = [];
  newFT = "";
  newVC = "";
  newVMM = "";
  newVMB = "";

  constructor(private codebookService: CodebookService) { }

  ngOnInit(): void {
    this.getFuelTypes();
    this.getVehicleClasses();
    this.getVehicleModels();
  }

  getFuelTypes() {
    this.codebookService.getFuelTypes()
      .subscribe(
        (data: any) => {
          console.log('Got fuel types.');
          this.fuelTypes = Object.assign([], (data));
        }, (error) => alert(error.text)
      )
  }

  getVehicleClasses() {
    this.codebookService.getVehicleClasses()
      .subscribe(
        (data: any) => {
          console.log('Got vehicle classes.');
          this.vehicleClasses = Object.assign([], (data));
        }, (error) => alert(error.text)
      )
  }

  getVehicleModels() {
    this.codebookService.getVehicleModels()
      .subscribe(
        (data: any) => {
          console.log('Got vehicle models.');
          this.vehicleModels = Object.assign([], (data));
        }, (error) => alert(error.text)
      )
  }

  addFuelType() {
    this.codebookService.newFuelType(this.newFT)
      .subscribe(
        (data: any) => {
          console.log('Fuel type added');
          this.getFuelTypes();
        }, (error) => alert(error.text)
      );
  }

  addVehicleClass() {
    this.codebookService.newVehicleClass(this.newVC)
      .subscribe(
        (data: any) => {
          console.log('Vehicle class added');
          this.getVehicleClasses();
        }, (error) => alert(error.text)
      );
  }

  addVehicleModel() {
    this.codebookService.newVehicleModel(this.newVMM, this.newVMB)
      .subscribe(
        (data: any) => {
          console.log('Vehicle model added');
          this.getVehicleModels();
        }, (error) => alert(error.text)
      );
  }

  deleteFuelType(id: number) {
    this.codebookService.deleteFuelType(id)
      .subscribe(
        (data: any) => {
          console.log('Fuel type deleted');
          this.getFuelTypes();
        }, (error) => alert(error.text)
      );
  }

  deleteVehicleClass(id: number) {
    this.codebookService.deleteVehicleClass(id)
      .subscribe(
        (data: any) => {
          console.log('Vehicle class deleted');
          this.getVehicleClasses();
        }, (error) => alert(error.text)
      );
  }

  deleteVehicleModel(id: number) {
    this.codebookService.deleteVehicleModel(id)
      .subscribe(
        (data: any) => {
          console.log('Vehicle model deleted');
          this.getVehicleModels();
        }, (error) => alert(error.text)
      );
  }
}

import { Component, OnInit } from '@angular/core';
import { CodebookService } from '../services/codebook.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NewFuelTypeComponent } from '../new-fuel-type/new-fuel-type.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  activeInputFT = [];
  activeInputVC = [];
  activeInputVM = [];

  constructor(public dialog: MatDialog,
    private modalService: NgbModal,
    private codebookService: CodebookService) { }

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

          this.activeInputFT = [];
          for(let i=0;i<this.fuelTypes.length;i++) {
            this.activeInputFT.push(false);
          }

          console.log(this.activeInputFT);
        }, (error) => alert(error.text)
      )
  }

  getVehicleClasses() {
    this.codebookService.getVehicleClasses()
      .subscribe(
        (data: any) => {
          console.log('Got vehicle classes.');
          this.vehicleClasses = Object.assign([], (data));

          this.activeInputVC = [];
          for(let i=0;i<this.vehicleClasses.length;i++) {
            this.activeInputVC.push(false);
          }

          console.log(this.activeInputVC);
        }, (error) => alert(error.text)
      )
  }

  getVehicleModels() {
    this.codebookService.getVehicleModels()
      .subscribe(
        (data: any) => {
          console.log('Got vehicle models.');
          this.vehicleModels = Object.assign([], (data));

          this.activeInputVM = [];
          for(let i=0;i<this.vehicleModels.length;i++) {
            this.activeInputVM.push(false);
          }

          console.log(this.activeInputVM);
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

  aaa() {
    const dialogRef = this.dialog.open(NewFuelTypeComponent);
  }
}

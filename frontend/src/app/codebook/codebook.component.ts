import { Component, OnInit } from '@angular/core';
import { CodebookService } from '../services/codebook/codebook.service';
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
  activeInputVMM = [];
  activeInputVMB = [];
  fuelTypeNames = [];
  vehicleClassNames = [];
  vehicleBrands = [];
  vehicleModelNames = [];

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
          this.fuelTypeNames = [];
          for (let i = 0; i < this.fuelTypes.length; i++) {
            this.activeInputFT.push(true);
            this.fuelTypeNames.push(this.fuelTypes[i].fuelType);
          }

          console.log(this.activeInputFT);
          console.log(this.fuelTypeNames);
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
          this.vehicleClassNames = [];
          for (let i = 0; i < this.vehicleClasses.length; i++) {
            this.activeInputVC.push(true);
            this.vehicleClassNames.push(this.vehicleClasses[i].vehicleClass);
          }

          console.log(this.activeInputVC);
          console.log(this.vehicleClassNames);
        }, (error) => alert(error.text)
      )
  }

  getVehicleModels() {
    this.codebookService.getVehicleModels()
      .subscribe(
        (data: any) => {
          console.log('Got vehicle models.');
          this.vehicleModels = Object.assign([], (data));

          this.activeInputVMM = [];
          this.activeInputVMB = [];
          this.vehicleBrands = [];
          this.vehicleModelNames = [];
          for (let i = 0; i < this.vehicleModels.length; i++) {
            this.activeInputVMM.push(true);
            this.activeInputVMB.push(true);
            this.vehicleBrands.push(this.vehicleModels[i].brand);
            this.vehicleModelNames.push(this.vehicleModels[i].model);
          }

          console.log(this.activeInputVMM);
          console.log(this.activeInputVMB);
          console.log(this.vehicleBrands);
          console.log(this.vehicleModelNames);
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

  editFT(i: any) {
    this.activeInputFT[i] = !this.activeInputFT[i];
  }

  editVC(i: any) {
    this.activeInputVC[i] = !this.activeInputVC[i];
  }

  editVMB(i: any) {
    this.activeInputVMB[i] = !this.activeInputVMB[i];
  }

  editVMM(i: any) {
    this.activeInputVMM[i] = !this.activeInputVMM[i];
  }

  updateFT(i: any, id: number) {
    this.activeInputFT[i] = !this.activeInputFT[i];
    this.codebookService.updateFuelType(this.fuelTypeNames[i], id)
      .subscribe(
        (data: any) => {
          console.log('Fuel type updated');
          this.getFuelTypes();
        }, (error) => alert(error.text)
      );
  }

  updateVC(i: any, id: number) {
    this.activeInputVC[i] = !this.activeInputVC[i];
    this.codebookService.updateVehicleClass(this.vehicleClassNames[i], id)
      .subscribe(
        (data: any) => {
          console.log('Vehicle class updated');
          this.getVehicleClasses();
        }, (error) => alert(error.text)
      );
  }

  updateVMB(i: any, id: number) {
    this.activeInputVMB[i] = !this.activeInputVMB[i];
    this.codebookService.updateVehicleModel(this.vehicleModelNames[i], this.vehicleBrands[i], id)
      .subscribe(
        (data: any) => {
          console.log('Vehicle brand updated');
          this.getVehicleModels();
        }, (error) => alert(error.text)
      );
  }

  updateVMM(i: any, id: number) {
    this.activeInputVMM[i] = !this.activeInputVMM[i];
    this.codebookService.updateVehicleModel(this.vehicleModelNames[i], this.vehicleBrands[i], id)
      .subscribe(
        (data: any) => {
          console.log('Vehicle model updated');
          this.getVehicleModels();
        }, (error) => alert(error.text)
      );
  }
}

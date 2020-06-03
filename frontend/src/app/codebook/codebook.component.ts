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
}

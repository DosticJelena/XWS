import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogData } from '../DialogData';

@Component({
  selector: 'app-new-fuel-type',
  templateUrl: './new-fuel-type.component.html',
  styleUrls: ['./new-fuel-type.component.css']
})
export class NewFuelTypeComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<NewFuelTypeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
  }

}

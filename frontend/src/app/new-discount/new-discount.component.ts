import { Component, OnInit } from '@angular/core';
import { DiscountService } from '../services/discount/discount.service';

@Component({
  selector: 'app-new-discount',
  templateUrl: './new-discount.component.html',
  styleUrls: ['./new-discount.component.css']
})
export class NewDiscountComponent implements OnInit {

  amount : number;

  constructor(private discountService : DiscountService) { }

  ngOnInit(): void {
  }
  submitNewAmmount() {
    if(this.amount != undefined) {
      this.discountService.addNewDiscount(this.amount).subscribe(
        (data : any) => {
            console.log(data);
        },
        (error: any) => {
          console.log(error);
        }
      )
  
    }
    }

}

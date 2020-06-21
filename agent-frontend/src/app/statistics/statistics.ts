import { Component } from '@angular/core'
import { StatisticsService } from '../services/statistics/statistics.service';

@Component({
    selector: 'statistics',
    templateUrl: './statistics.html',
    styleUrls: ['./statistics.css']
})
export class Statistics {

    mostDistance:any;
    mostComments:any;
    bestGrade:any;

    constructor(public statService:StatisticsService){

    }

    
    ngOnInit(){
        this.statService.mostDistance(1).subscribe(
            (data: any) => {
              this.mostDistance = data;
              console.log(data);
            }, (error) => alert(error.text)
          );

        this.statService.mostComments(1).subscribe(
          (data: any) => {
            this.mostComments = data;
          }, (error) => alert(error.text)
        );

        this.statService.bestGrade(1).subscribe(
            (data: any) => {
              this.bestGrade = data;
            }, (error) => alert(error.text)
          );
    }


}
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarsService } from '../cars.service';
import { CartService } from 'src/app/services/cart/cart.service';
import { CloseScrollStrategy } from '@angular/cdk/overlay';
import { MessageService } from 'src/app/services/message/message.service';
import { CommentsService } from 'src/app/services/comment/comment.service';
import { GradeAndCommentService } from 'src/app/services/grade-and-comment/grade-and-comment.service';

@Component({
    selector: 'car-details',
    templateUrl: './car-details.html',
    styleUrls: ['./car-details.css']
})
export class CarDetails {
    car:any;
    images:any;
    comments: [];
    grades: [];
    avgGrade: number;

    loggedUser = JSON.parse(localStorage.getItem("loggedUser"));

    constructor(private route:ActivatedRoute, 
      private carsService:CarsService,
      private cartService : CartService,
      private commentSrevice: CommentsService,
      private gradingService: GradeAndCommentService){

    }

    ngOnInit(){
        this.carsService.getCar(this.route.snapshot.params['id']).subscribe(
            (data: any) => {
              this.car = data;
            }, (error) => alert(error.text)
          );

        this.carsService.getImages(this.route.snapshot.params['id']).subscribe(
          (data: any) => {
            this.images = data;
          }, (error) => alert(error.text)
        );

        this.commentSrevice.getVehicleComments(this.route.snapshot.params['id']).subscribe(
          (data: any) => {
            this.comments = data;
          }, (error) => alert(error.text)
        );

        this.gradingService.getCarGrades(this.route.snapshot.params['id']).subscribe(
          (data: any) => {
            this.grades = data;
            if (data.length != 0){
              let sum = 0;
              data.forEach(grade => {
                sum += grade.value 
              });
              this.avgGrade = Math.round((sum/data.length) * 100) / 100;
            } else {
              this.avgGrade = 0.0;
            }
          }, (error) => alert(error.text)
        );
    }
    
    addToCart() {
      this.cartService.addToCart(JSON.parse(localStorage.getItem('loggedUser')).id,this.car.id).subscribe(
        (data : any) => {
          console.log(data);

        },(error : any) => {
          console.log(error);
        })

    }
}
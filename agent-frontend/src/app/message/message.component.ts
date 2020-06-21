import { Component, OnInit } from '@angular/core';
import {MessageService} from '../services/message/message.service'
import {AuthService} from '../auth/auth.service'
import {GradeAndCommentService} from '../services/grade-and-comment/grade-and-comment.service'

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  reservations = [];
  messages = [];
  map = new Map<number, string>();
  counter = 0;
  grade = 0;
  
  constructor(private MessageService: MessageService,
              private AuthService: AuthService) { }
  
  ngOnInit(): void {
    this.reloadReservations();
    this.reloadMessages();
    
  }
 

  reloadReservations() {
    this.MessageService.reload(1)
      .subscribe(
        (data: any) => {          
          this.reservations = Object.assign([], (data));
          console.log(this.reservations);
        }, (error) => alert(error.text)
      );
  }
  reloadMessages(){
    this.MessageService.getAllMessages(1)
      .subscribe(
        (data: any) => {
          this.messages =  Object.assign([], (data));;
        }, (error) => alert(error.text)
      );
  }
  sendMess(receiver,i) {
    this.MessageService.sendMess(1,receiver,(<HTMLInputElement>document.getElementById(i)).value)
      .subscribe(
        (data: any) => {
          console.log(data);
          (<HTMLInputElement>document.getElementById(i)).value = "";
          this.reloadMessages();
        }, (error) => alert(error.text)
      );
  }
 
  changeTable(){
    if(this.counter === 0){
      (<HTMLInputElement>document.getElementById("PENDING")).style.display="none" ;
      (<HTMLInputElement>document.getElementById("RESERVED")).style.display="block" ;

    }
  }
}

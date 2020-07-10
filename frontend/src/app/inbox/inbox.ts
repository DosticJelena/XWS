import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message/message.service'
import { AuthService } from '../auth/auth.service'
import { GradeAndCommentService } from '../services/grade-and-comment/grade-and-comment.service'

@Component({
  selector: 'inbox',
  templateUrl: './inbox.html',
  styleUrls: ['./inbox.css']
})
export class Inbox implements OnInit {

  messages: [];
  loggedUser = JSON.parse(localStorage.getItem("loggedUser"));
  visible = -1;
  map = new Map<number, string>();
  users= [];

  constructor(private MessageService: MessageService, private AuthService:AuthService) { }

  ngOnInit(): void {
    this.MessageService.getMessages()
    .subscribe(
      (data: any) => {
        console.log(data);
        this.messages = data;
      }, (error) => alert(error.text)
    );

    this.getUsers();
  }
  
  showContent(msgId:number) {
    if (this.visible === msgId){
      this.visible = -1;
    } else {
      this.visible = msgId;
    }
  }

  getUsers() {
    this.AuthService.getUsers()
      .subscribe(
        (data: any) => {
          this.users = Object.assign([], (data));
          this.setUserName();
        }, (error) => alert(error.text)
      );
  }

  setUserName() {
    this.users.forEach(user => {
      console.log(user)
      this.map.set(user.id, user.username);
    });
    console.log(this.map);
  }
  
  sendMess(receiver, i) {
    this.MessageService.sendMess(receiver, JSON.parse(localStorage.getItem("loggedUser")).id, (<HTMLInputElement>document.getElementById(i)).value)
      .subscribe(
        (data: any) => {
          console.log(data);
          (<HTMLInputElement>document.getElementById(i)).value = "";
        }, (error) => alert(error.text)
      );
  }

}

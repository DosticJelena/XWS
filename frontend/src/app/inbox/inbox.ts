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

  constructor(private MessageService: MessageService) { }

  ngOnInit(): void {
    this.MessageService.getMessages()
    .subscribe(
      (data: any) => {
        console.log(data);
        this.messages = data;
      }, (error) => alert(error.text)
    );
  }
  
  showContent(msgId:number) {
    if (this.visible === msgId){
      this.visible = -1;
    } else {
      this.visible = msgId;
    }
  }

}

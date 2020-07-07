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

  constructor(private MessageService: MessageService,
    private AuthService: AuthService,
    private GradeAndCommentService: GradeAndCommentService) { }

  ngOnInit(): void {

  }
}

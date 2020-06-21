import { Component, OnInit } from '@angular/core';
import {CommentsService} from '../services/comment/comment.service'

@Component({
  selector: 'app-manage-comments',
  templateUrl: './manage-comments.component.html',
  styleUrls: ['./manage-comments.component.css']
})
export class ManageCommentsComponent implements OnInit {
  comments = [];
  constructor(private CommentsService: CommentsService) { }

  ngOnInit(): void {
    this.reloadComments()
  }
  reloadComments() {
    this.CommentsService.getPendingComments()
      .subscribe(
        (data: any) => {
          console.log('Got registered.');
          console.log(data);
          this.comments = Object.assign([], (data));
        }, (error) => alert(error.text)
      );
  }
  approve(id) {
    this.CommentsService.approve(id)
      .subscribe(
        (data: any) => {
          console.log('Comm activated!');
          this.reloadComments();
        }, (error) => alert(error.text)
      );
  }
  reject(id) {
    this.CommentsService.reject(id)
      .subscribe(
        (data: any) => {
          console.log('Comm rejected!');
          this.reloadComments();
        }, (error) => alert(error.text)
      );
  }

}

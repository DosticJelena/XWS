import { TestBed } from '@angular/core/testing';

import { GradeAndCommentService } from './grade-and-comment.service';

describe('GradeAndCommentService', () => {
  let service: GradeAndCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GradeAndCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

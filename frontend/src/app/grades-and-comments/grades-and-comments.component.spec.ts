import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradesAndCommentsComponent } from './grades-and-comments.component';

describe('GradesAndCommentsComponent', () => {
  let component: GradesAndCommentsComponent;
  let fixture: ComponentFixture<GradesAndCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradesAndCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradesAndCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

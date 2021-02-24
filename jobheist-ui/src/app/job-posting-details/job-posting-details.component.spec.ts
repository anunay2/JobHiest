import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobPostingDetailsComponent } from './job-posting-details.component';

describe('JobPostingDetailsComponent', () => {
  let component: JobPostingDetailsComponent;
  let fixture: ComponentFixture<JobPostingDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobPostingDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobPostingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

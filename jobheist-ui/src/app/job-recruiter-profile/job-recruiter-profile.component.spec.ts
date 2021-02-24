import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobRecruiterProfileComponent } from './job-recruiter-profile.component';

describe('JobRecruiterProfileComponent', () => {
  let component: JobRecruiterProfileComponent;
  let fixture: ComponentFixture<JobRecruiterProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobRecruiterProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobRecruiterProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

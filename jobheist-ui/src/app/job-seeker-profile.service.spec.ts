import { TestBed } from '@angular/core/testing';

import { JobSeekerProfileService } from './job-seeker-profile.service';

describe('JobSeekerProfileService', () => {
  let service: JobSeekerProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobSeekerProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

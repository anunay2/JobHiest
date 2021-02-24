import { TestBed } from '@angular/core/testing';

import { JobRecruiterProfileService } from './job-recruiter-profile.service';

describe('JobRecruiterProfileService', () => {
  let service: JobRecruiterProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobRecruiterProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

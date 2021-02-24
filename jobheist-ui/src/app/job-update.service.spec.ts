import { TestBed } from '@angular/core/testing';

import { JobUpdateService } from './job-update.service';

describe('JobUpdateService', () => {
  let service: JobUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

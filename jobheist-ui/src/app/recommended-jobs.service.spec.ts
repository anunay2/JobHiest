import { TestBed } from '@angular/core/testing';

import { RecommendedJobsService } from './recommended-jobs.service';

describe('RecommendedJobsService', () => {
  let service: RecommendedJobsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecommendedJobsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

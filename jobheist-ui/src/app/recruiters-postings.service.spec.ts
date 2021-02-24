import { TestBed } from '@angular/core/testing';

import { RecruitersPostingsService } from './recruiters-postings.service';

describe('RecruitersPostingsService', () => {
  let service: RecruitersPostingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecruitersPostingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

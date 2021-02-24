import { TestBed } from '@angular/core/testing';

import { ShortlistingService } from './shortlisting.service';

describe('ShortlistingService', () => {
  let service: ShortlistingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShortlistingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

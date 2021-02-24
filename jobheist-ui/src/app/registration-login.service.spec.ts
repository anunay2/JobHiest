import { TestBed } from '@angular/core/testing';

import { RegistrationLoginService } from './registration-login.service';

describe('RegistrationLoginService', () => {
  let service: RegistrationLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

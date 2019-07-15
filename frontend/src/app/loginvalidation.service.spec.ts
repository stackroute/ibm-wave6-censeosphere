import { TestBed } from '@angular/core/testing';

import { LoginvalidationService } from './loginvalidation.service';

describe('LoginvalidationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoginvalidationService = TestBed.get(LoginvalidationService);
    expect(service).toBeTruthy();
  });
});

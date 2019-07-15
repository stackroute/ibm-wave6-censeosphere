import { TestBed } from '@angular/core/testing';

import { ProdownerserviceService } from './prodownerservice.service';

describe('ProdownerserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProdownerserviceService = TestBed.get(ProdownerserviceService);
    expect(service).toBeTruthy();
  });
});

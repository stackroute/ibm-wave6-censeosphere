import { TestBed } from '@angular/core/testing';

import { ReviewConnectionService } from './review-connection.service';

describe('ReviewConnectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewConnectionService = TestBed.get(ReviewConnectionService);
    expect(service).toBeTruthy();
  });
});

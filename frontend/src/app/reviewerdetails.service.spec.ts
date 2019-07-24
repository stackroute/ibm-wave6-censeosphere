import { TestBed } from '@angular/core/testing';

import { ReviewerdetailsService } from './reviewerdetails.service';

describe('ReviewerdetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReviewerdetailsService = TestBed.get(ReviewerdetailsService);
    expect(service).toBeTruthy();
  });
});

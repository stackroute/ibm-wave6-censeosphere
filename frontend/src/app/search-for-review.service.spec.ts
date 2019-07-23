import { TestBed } from '@angular/core/testing';

import { SearchForReviewService } from './search-for-review.service';

describe('SearchForReviewService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchForReviewService = TestBed.get(SearchForReviewService);
    expect(service).toBeTruthy();
  });
});

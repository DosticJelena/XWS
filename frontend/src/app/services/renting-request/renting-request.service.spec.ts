import { TestBed } from '@angular/core/testing';

import { RentingRequestService } from './renting-request.service';

describe('RentingRequestService', () => {
  let service: RentingRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RentingRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

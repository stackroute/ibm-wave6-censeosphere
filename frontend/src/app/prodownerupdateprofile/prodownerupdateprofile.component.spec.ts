import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdownerupdateprofileComponent } from './prodownerupdateprofile.component';

describe('ProdownerupdateprofileComponent', () => {
  let component: ProdownerupdateprofileComponent;
  let fixture: ComponentFixture<ProdownerupdateprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdownerupdateprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdownerupdateprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

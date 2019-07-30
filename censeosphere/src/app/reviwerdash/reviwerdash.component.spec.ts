import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviwerdashComponent } from './reviwerdash.component';

describe('ReviwerdashComponent', () => {
  let component: ReviwerdashComponent;
  let fixture: ComponentFixture<ReviwerdashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviwerdashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviwerdashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

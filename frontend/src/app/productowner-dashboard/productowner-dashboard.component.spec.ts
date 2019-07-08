import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductownerDashboardComponent } from './productowner-dashboard.component';

describe('ProductownerDashboardComponent', () => {
  let component: ProductownerDashboardComponent;
  let fixture: ComponentFixture<ProductownerDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductownerDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductownerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductownerdashboardComponent } from './productownerdashboard.component';

describe('ProductownerdashboardComponent', () => {
  let component: ProductownerdashboardComponent;
  let fixture: ComponentFixture<ProductownerdashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductownerdashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductownerdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

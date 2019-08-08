import { async, TestBed } from '@angular/core/testing';
import { ProductownerdashboardComponent } from './productownerdashboard.component';
describe('ProductownerdashboardComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProductownerdashboardComponent]
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
//# sourceMappingURL=productownerdashboard.component.spec.js.map
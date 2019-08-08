import { async, TestBed } from '@angular/core/testing';
import { ProductlistGuestComponent } from './productlist-guest.component';
describe('ProductlistGuestComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProductlistGuestComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(ProductlistGuestComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=productlist-guest.component.spec.js.map
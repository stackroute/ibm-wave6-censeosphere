import { async, TestBed } from '@angular/core/testing';
import { ReviwerdashComponent } from './reviwerdash.component';
describe('ReviwerdashComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ReviwerdashComponent]
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
//# sourceMappingURL=reviwerdash.component.spec.js.map
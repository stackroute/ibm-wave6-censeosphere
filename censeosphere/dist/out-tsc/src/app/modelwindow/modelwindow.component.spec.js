import { async, TestBed } from '@angular/core/testing';
import { ModelwindowComponent } from './modelwindow.component';
describe('ModelwindowComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ModelwindowComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(ModelwindowComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=modelwindow.component.spec.js.map
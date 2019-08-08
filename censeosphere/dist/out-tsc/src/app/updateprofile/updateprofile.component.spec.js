import { async, TestBed } from '@angular/core/testing';
import { UpdateprofileComponent } from './updateprofile.component';
describe('UpdateprofileComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [UpdateprofileComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(UpdateprofileComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=updateprofile.component.spec.js.map
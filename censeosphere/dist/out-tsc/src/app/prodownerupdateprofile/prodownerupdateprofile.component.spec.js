import { async, TestBed } from '@angular/core/testing';
import { ProdownerupdateprofileComponent } from './prodownerupdateprofile.component';
describe('ProdownerupdateprofileComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ProdownerupdateprofileComponent]
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
//# sourceMappingURL=prodownerupdateprofile.component.spec.js.map
import { async, TestBed } from '@angular/core/testing';
import { SearchForReviewComponent } from './search-for-review.component';
describe('SearchForReviewComponent', () => {
    let component;
    let fixture;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SearchForReviewComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(SearchForReviewComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=search-for-review.component.spec.js.map
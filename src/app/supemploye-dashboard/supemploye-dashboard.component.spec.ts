import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupemployeDashboardComponent } from './supemploye-dashboard.component';

describe('SupemployeDashboardComponent', () => {
  let component: SupemployeDashboardComponent;
  let fixture: ComponentFixture<SupemployeDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupemployeDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupemployeDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

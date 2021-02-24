import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPostingsComponent } from './all-postings.component';

describe('AllPostingsComponent', () => {
  let component: AllPostingsComponent;
  let fixture: ComponentFixture<AllPostingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllPostingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPostingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

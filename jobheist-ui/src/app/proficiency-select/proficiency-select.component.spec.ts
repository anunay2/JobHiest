import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProficiencySelectComponent } from './proficiency-select.component';

describe('ProficiencySelectComponent', () => {
  let component: ProficiencySelectComponent;
  let fixture: ComponentFixture<ProficiencySelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProficiencySelectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProficiencySelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

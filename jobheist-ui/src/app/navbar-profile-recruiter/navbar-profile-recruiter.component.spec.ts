import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarProfileRecruiterComponent } from './navbar-profile-recruiter.component';

describe('NavbarProfileRecruiterComponent', () => {
  let component: NavbarProfileRecruiterComponent;
  let fixture: ComponentFixture<NavbarProfileRecruiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarProfileRecruiterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarProfileRecruiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

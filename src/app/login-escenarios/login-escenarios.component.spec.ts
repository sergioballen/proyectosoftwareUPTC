import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginEscenariosComponent } from './login-escenarios.component';

describe('LoginEscenariosComponent', () => {
  let component: LoginEscenariosComponent;
  let fixture: ComponentFixture<LoginEscenariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginEscenariosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginEscenariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

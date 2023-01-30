import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimulationSpaceComponent } from './simulation-space.component';

describe('SimulationSpaceComponent', () => {
  let component: SimulationSpaceComponent;
  let fixture: ComponentFixture<SimulationSpaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimulationSpaceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SimulationSpaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

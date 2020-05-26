import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GeneratedProjectForTestsTestModule } from '../../../test.module';
import { UpperCamelClazzDetailComponent } from 'app/entities/upper-camel-clazz/upper-camel-clazz-detail.component';
import { UpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';

describe('Component Tests', () => {
  describe('UpperCamelClazz Management Detail Component', () => {
    let comp: UpperCamelClazzDetailComponent;
    let fixture: ComponentFixture<UpperCamelClazzDetailComponent>;
    const route = ({ data: of({ upperCamelClazz: new UpperCamelClazz(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeneratedProjectForTestsTestModule],
        declarations: [UpperCamelClazzDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UpperCamelClazzDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UpperCamelClazzDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load upperCamelClazz on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.upperCamelClazz).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

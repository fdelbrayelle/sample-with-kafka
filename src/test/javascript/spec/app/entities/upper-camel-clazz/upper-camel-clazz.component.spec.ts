import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GeneratedProjectForTestsTestModule } from '../../../test.module';
import { UpperCamelClazzComponent } from 'app/entities/upper-camel-clazz/upper-camel-clazz.component';
import { UpperCamelClazzService } from 'app/entities/upper-camel-clazz/upper-camel-clazz.service';
import { UpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';

describe('Component Tests', () => {
  describe('UpperCamelClazz Management Component', () => {
    let comp: UpperCamelClazzComponent;
    let fixture: ComponentFixture<UpperCamelClazzComponent>;
    let service: UpperCamelClazzService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeneratedProjectForTestsTestModule],
        declarations: [UpperCamelClazzComponent]
      })
        .overrideTemplate(UpperCamelClazzComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UpperCamelClazzComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UpperCamelClazzService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UpperCamelClazz(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.upperCamelClazzes && comp.upperCamelClazzes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

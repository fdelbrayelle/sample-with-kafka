import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GeneratedProjectForTestsTestModule } from '../../../test.module';
import { UpperCamelClazzUpdateComponent } from 'app/entities/upper-camel-clazz/upper-camel-clazz-update.component';
import { UpperCamelClazzService } from 'app/entities/upper-camel-clazz/upper-camel-clazz.service';
import { UpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';

describe('Component Tests', () => {
  describe('UpperCamelClazz Management Update Component', () => {
    let comp: UpperCamelClazzUpdateComponent;
    let fixture: ComponentFixture<UpperCamelClazzUpdateComponent>;
    let service: UpperCamelClazzService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GeneratedProjectForTestsTestModule],
        declarations: [UpperCamelClazzUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UpperCamelClazzUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UpperCamelClazzUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UpperCamelClazzService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UpperCamelClazz(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UpperCamelClazz();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

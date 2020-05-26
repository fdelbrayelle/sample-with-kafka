import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUpperCamelClazz, UpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';
import { UpperCamelClazzService } from './upper-camel-clazz.service';

@Component({
  selector: 'jhi-upper-camel-clazz-update',
  templateUrl: './upper-camel-clazz-update.component.html'
})
export class UpperCamelClazzUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected upperCamelClazzService: UpperCamelClazzService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ upperCamelClazz }) => {
      this.updateForm(upperCamelClazz);
    });
  }

  updateForm(upperCamelClazz: IUpperCamelClazz): void {
    this.editForm.patchValue({
      id: upperCamelClazz.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const upperCamelClazz = this.createFromForm();
    if (upperCamelClazz.id !== undefined) {
      this.subscribeToSaveResponse(this.upperCamelClazzService.update(upperCamelClazz));
    } else {
      this.subscribeToSaveResponse(this.upperCamelClazzService.create(upperCamelClazz));
    }
  }

  private createFromForm(): IUpperCamelClazz {
    return {
      ...new UpperCamelClazz(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUpperCamelClazz>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

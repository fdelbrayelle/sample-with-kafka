import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';
import { UpperCamelClazzService } from './upper-camel-clazz.service';

@Component({
  templateUrl: './upper-camel-clazz-delete-dialog.component.html'
})
export class UpperCamelClazzDeleteDialogComponent {
  upperCamelClazz?: IUpperCamelClazz;

  constructor(
    protected upperCamelClazzService: UpperCamelClazzService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.upperCamelClazzService.delete(id).subscribe(() => {
      this.eventManager.broadcast('upperCamelClazzListModification');
      this.activeModal.close();
    });
  }
}

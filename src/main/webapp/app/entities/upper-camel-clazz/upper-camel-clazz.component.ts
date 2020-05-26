import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';
import { UpperCamelClazzService } from './upper-camel-clazz.service';
import { UpperCamelClazzDeleteDialogComponent } from './upper-camel-clazz-delete-dialog.component';

@Component({
  selector: 'jhi-upper-camel-clazz',
  templateUrl: './upper-camel-clazz.component.html'
})
export class UpperCamelClazzComponent implements OnInit, OnDestroy {
  upperCamelClazzes?: IUpperCamelClazz[];
  eventSubscriber?: Subscription;

  constructor(
    protected upperCamelClazzService: UpperCamelClazzService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.upperCamelClazzService.query().subscribe((res: HttpResponse<IUpperCamelClazz[]>) => (this.upperCamelClazzes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUpperCamelClazzes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUpperCamelClazz): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUpperCamelClazzes(): void {
    this.eventSubscriber = this.eventManager.subscribe('upperCamelClazzListModification', () => this.loadAll());
  }

  delete(upperCamelClazz: IUpperCamelClazz): void {
    const modalRef = this.modalService.open(UpperCamelClazzDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.upperCamelClazz = upperCamelClazz;
  }
}

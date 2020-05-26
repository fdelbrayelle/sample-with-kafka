import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';

@Component({
  selector: 'jhi-upper-camel-clazz-detail',
  templateUrl: './upper-camel-clazz-detail.component.html'
})
export class UpperCamelClazzDetailComponent implements OnInit {
  upperCamelClazz: IUpperCamelClazz | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ upperCamelClazz }) => (this.upperCamelClazz = upperCamelClazz));
  }

  previousState(): void {
    window.history.back();
  }
}

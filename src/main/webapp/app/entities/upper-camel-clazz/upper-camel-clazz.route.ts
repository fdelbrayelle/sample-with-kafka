import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUpperCamelClazz, UpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';
import { UpperCamelClazzService } from './upper-camel-clazz.service';
import { UpperCamelClazzComponent } from './upper-camel-clazz.component';
import { UpperCamelClazzDetailComponent } from './upper-camel-clazz-detail.component';
import { UpperCamelClazzUpdateComponent } from './upper-camel-clazz-update.component';

@Injectable({ providedIn: 'root' })
export class UpperCamelClazzResolve implements Resolve<IUpperCamelClazz> {
  constructor(private service: UpperCamelClazzService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUpperCamelClazz> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((upperCamelClazz: HttpResponse<UpperCamelClazz>) => {
          if (upperCamelClazz.body) {
            return of(upperCamelClazz.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UpperCamelClazz());
  }
}

export const upperCamelClazzRoute: Routes = [
  {
    path: '',
    component: UpperCamelClazzComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UpperCamelClazzes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UpperCamelClazzDetailComponent,
    resolve: {
      upperCamelClazz: UpperCamelClazzResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UpperCamelClazzes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UpperCamelClazzUpdateComponent,
    resolve: {
      upperCamelClazz: UpperCamelClazzResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UpperCamelClazzes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UpperCamelClazzUpdateComponent,
    resolve: {
      upperCamelClazz: UpperCamelClazzResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UpperCamelClazzes'
    },
    canActivate: [UserRouteAccessService]
  }
];

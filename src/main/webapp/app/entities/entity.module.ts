import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'foo',
        loadChildren: () => import('./foo/foo.module').then(m => m.GeneratedProjectForTestsFooModule)
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.GeneratedProjectForTestsOrderModule)
      },
      {
        path: 'upper-camel-clazz',
        loadChildren: () =>
          import('./upper-camel-clazz/upper-camel-clazz.module').then(m => m.GeneratedProjectForTestsUpperCamelClazzModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GeneratedProjectForTestsEntityModule {}

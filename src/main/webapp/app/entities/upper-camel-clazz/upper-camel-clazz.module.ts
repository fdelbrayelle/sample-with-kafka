import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GeneratedProjectForTestsSharedModule } from 'app/shared/shared.module';
import { UpperCamelClazzComponent } from './upper-camel-clazz.component';
import { UpperCamelClazzDetailComponent } from './upper-camel-clazz-detail.component';
import { UpperCamelClazzUpdateComponent } from './upper-camel-clazz-update.component';
import { UpperCamelClazzDeleteDialogComponent } from './upper-camel-clazz-delete-dialog.component';
import { upperCamelClazzRoute } from './upper-camel-clazz.route';

@NgModule({
  imports: [GeneratedProjectForTestsSharedModule, RouterModule.forChild(upperCamelClazzRoute)],
  declarations: [
    UpperCamelClazzComponent,
    UpperCamelClazzDetailComponent,
    UpperCamelClazzUpdateComponent,
    UpperCamelClazzDeleteDialogComponent
  ],
  entryComponents: [UpperCamelClazzDeleteDialogComponent]
})
export class GeneratedProjectForTestsUpperCamelClazzModule {}

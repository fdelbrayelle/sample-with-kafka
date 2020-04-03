import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GeneratedProjectForTestsSharedModule } from 'app/shared/shared.module';

import { MetricsComponent } from './metrics.component';

import { metricsRoute } from './metrics.route';

@NgModule({
  imports: [GeneratedProjectForTestsSharedModule, RouterModule.forChild([metricsRoute])],
  declarations: [MetricsComponent]
})
export class MetricsModule {}

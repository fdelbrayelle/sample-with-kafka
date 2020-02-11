import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GeneratedProjectForTestsSharedModule } from 'app/shared/shared.module';
import { GeneratedProjectForTestsCoreModule } from 'app/core/core.module';
import { GeneratedProjectForTestsAppRoutingModule } from './app-routing.module';
import { GeneratedProjectForTestsHomeModule } from './home/home.module';
import { GeneratedProjectForTestsEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    GeneratedProjectForTestsSharedModule,
    GeneratedProjectForTestsCoreModule,
    GeneratedProjectForTestsHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GeneratedProjectForTestsEntityModule,
    GeneratedProjectForTestsAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class GeneratedProjectForTestsAppModule {}

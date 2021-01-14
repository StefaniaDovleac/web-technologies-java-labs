import { ModuleWithProviders, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { DxTextBoxModule, DxButtonModule, DxValidatorModule, DxLoadPanelModule, DxTreeListModule, DxDataGridModule } from 'devextreme-angular';
import { CategoryDirective } from './directives/category.directive';
import { AuthInterceptor } from '../users/auth-interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  declarations: [CategoryDirective],
  imports: [
    CommonModule,
    DxTextBoxModule,
    DxButtonModule,
    DxValidatorModule,
    ReactiveFormsModule,
    DxLoadPanelModule,
    DxTreeListModule,
    DxDataGridModule
  ],
  exports: [
    DxTextBoxModule,
    DxButtonModule,
    DxValidatorModule,
    ReactiveFormsModule,
    DxLoadPanelModule,
    DxTreeListModule,
    CategoryDirective,
    DxDataGridModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders<SharedModule> {
    return {
      ngModule: SharedModule,
    };
  }
}

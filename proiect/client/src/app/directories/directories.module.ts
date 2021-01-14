import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DirectoriesPageComponent } from './directories-page/directories-page.component';
import { DirectoriesRoutingModule } from './directories-routing.module';
import { DxFileManagerModule } from 'devextreme-angular';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [DirectoriesPageComponent],
  imports: [
    CommonModule,
    DirectoriesRoutingModule,
    DxFileManagerModule,
    SharedModule,
  ]
})
export class DirectoriesModule { }

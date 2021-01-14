import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LogoutComponent } from './logout/logout.component';



@NgModule({
  declarations: [LogoutComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([{ path: '', component: LogoutComponent }])
  ]
})
export class LogoutModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DirectoriesPageComponent } from './directories-page/directories-page.component';
import { AuthGuard } from '../users/auth.guard';

const routes: Routes = [
  {
    path: 'all',
    component: DirectoriesPageComponent,
    canActivate: [AuthGuard]
  },

]


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DirectoriesRoutingModule { }

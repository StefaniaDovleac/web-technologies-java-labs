import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../users/auth.guard';
import { CategoryPageComponent } from './category-page/category-page.component';
import { CategoryListComponent } from './category-list/category-list.component';

const routes: Routes = [
  {
    path: 'all',
    component: CategoryListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: CategoryPageComponent,
    canActivate: [AuthGuard]
  },
]


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriesRoutingModule { }

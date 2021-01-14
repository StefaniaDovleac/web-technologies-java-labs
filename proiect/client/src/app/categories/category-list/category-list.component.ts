import { Component, OnInit } from '@angular/core';
import { CategoriesQuery } from '../state/categories.query';
import { CategoriesService } from '../state/categories.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {
  categories$ = this.query.select(q => q.categories);
  constructor(private query: CategoriesQuery, private service: CategoriesService) { }

  ngOnInit() {
    this.service.initializeList();
  }

  onAdd() {
    this.service.add();
  }


  onRowClick(event: any) {
    this.service.openEdit(event?.data?.id);
  }
}

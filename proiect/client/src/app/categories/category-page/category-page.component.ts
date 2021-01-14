import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { takeWhile } from 'rxjs/operators';
import { CategoriesQuery } from '../state/categories.query';
import { CategoriesService } from '../state/categories.service';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss']
})
export class CategoryPageComponent implements OnInit, AfterViewInit, OnDestroy {
  form: FormGroup;
  isDestroyed: boolean
  constructor(private fb: FormBuilder, private query: CategoriesQuery, private service: CategoriesService) {
    this.form = fb.group({
      id: null,
      name: null,
      description: null,
    })
  }

  ngOnInit() {

    // this.service.initialize();
  }

  ngAfterViewInit() {
    this.query.select(q => q.currentCategory).pipe(takeWhile(_ => !this.isDestroyed)).subscribe(c => this.form.patchValue(c, { emitEvent: false }));
    this.form.valueChanges.pipe(takeWhile(_ => !this.isDestroyed)).subscribe(v => {
      this.service.updateCategory(v);
    });
  }

  onSave() {
    this.service.save();
  }

  onDelete() {
    this.service.delete();
  }


  ngOnDestroy() {
    this.isDestroyed = true;
  }
}

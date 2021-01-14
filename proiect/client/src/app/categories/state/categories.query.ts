import { Injectable } from '@angular/core';
import { Query } from '@datorama/akita';
import { filter } from 'rxjs/operators';
import { CategoriesState, CategoriesStore } from './categories.store';

@Injectable({ providedIn: 'root' })
export class CategoriesQuery extends Query<CategoriesState> {

    constructor(protected store: CategoriesStore) {
        super(store);
    }

}
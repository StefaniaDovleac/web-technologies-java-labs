import { Injectable } from '@angular/core';
import { Store, StoreConfig } from '@datorama/akita';
import { CategoryDTO } from '../categories-http.service';

export interface CategoriesState {
    categories: CategoryDTO[],
    currentCategoryId: number,
    currentCategory: CategoryDTO,
}

export function createInitialState(): CategoriesState {
    return {
        categories: [],
        currentCategoryId: null,
        currentCategory: null,
    };
}


@Injectable({ providedIn: 'root' })
@StoreConfig({
    name: 'categories',
    resettable: true,
    // deepFreezeFn
})
export class CategoriesStore extends Store<CategoriesState> {

    constructor() {
        super(createInitialState());
    }

}

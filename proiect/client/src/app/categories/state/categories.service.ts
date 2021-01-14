import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { CategoriesStore } from './categories.store';
import { CategoriesHttpService, CategoryDTO } from '../categories-http.service';
import { Router } from '@angular/router';
import { CategoryDirective } from 'src/app/shared/directives/category.directive';
import { arrayRemove } from '@datorama/akita';

@Injectable({ providedIn: 'root' })
export class CategoriesService {

    constructor(private store: CategoriesStore,
        private categoriesHttpService: CategoriesHttpService,
        private router: Router,
        private toastr: ToastrService) {
    }

    // async initialize() {
    //     try {
    //         this.store.setLoading(true);
    //         const id = this.store.getValue().currentCategoryId;
    //         let currentCategory;
    //         if (id == 0) {
    //             currentCategory = new CategoryDTO();
    //         } else {
    //             currentCategory = await this.categoriesHttpService.getBy(id).toPromise();
    //         }
    //         this.store.update({ currentCategory })
    //     } finally {
    //         this.store.getValue();
    //     }
    // }

    async initializeList() {
        try {
            this.store.setLoading(true);
            const categories = await this.categoriesHttpService.getAll().toPromise();
            this.store.update({
                categories
            })
        } finally {
            this.store.getValue();
        }
    }

    async add() {
        const currentCategory = new CategoryDTO();
        this.store.update({ currentCategory });
        await this.router.navigate(['categories', 0]);
    }

    async openEdit(id: number) {
        const currentCategory = await this.categoriesHttpService.getBy(id).toPromise();
        this.store.update({ currentCategory });
        await this.router.navigate(['categories', id]);
    }

    updateCategory(category: CategoryDTO) {
        this.store.update({
            currentCategory: category
        })
    }

    save() {
        try {
            this.store.setLoading(true);
            const currentCategory = this.store.getValue().currentCategory;
            if (currentCategory.id == 0) {
                this.create(currentCategory);
            }
            else {
                this.update(currentCategory);
            }
        }
        finally {
            this.store.setLoading(false);
        }
    }

    async create(category: CategoryDTO) {
        const result = await this.categoriesHttpService.create(category).toPromise();
        if (result) {
            this.store.update({
                currentCategory: result
            });
            alert("Saved")
            await this.router.navigate(['categories', result.id])
        }
    }

    async update(category: CategoryDTO) {
        const result = await this.categoriesHttpService.update(category).toPromise();
        if (result) {
            this.store.update({
                currentCategory: result
            });
            alert("Updated")
        }
    }

    async delete() {
        const id = this.store.getValue().currentCategory.id;
        const result = await this.categoriesHttpService.delete(id).toPromise();
        if (result) {
            this.store.update({
                currentCategory: null
            });
            this.store.update({
                categories: arrayRemove(this.store.getValue().categories, id)
            })
            await this.router.navigate(['categories', "all"])
        }
    }


}



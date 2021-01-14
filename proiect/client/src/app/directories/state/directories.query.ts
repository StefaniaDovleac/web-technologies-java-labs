import { Injectable } from '@angular/core';
import { Query } from '@datorama/akita';
import { filter } from 'rxjs/operators';
import { DirectoriesState, DirectoriesStore } from './directories.store';

@Injectable({ providedIn: 'root' })
export class DirectoriesQuery extends Query<DirectoriesState> {

    constructor(protected store: DirectoriesStore) {
        super(store);
    }

}
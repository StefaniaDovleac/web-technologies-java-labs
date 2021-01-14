import { Injectable } from '@angular/core';
import { Query } from '@datorama/akita';
import { filter } from 'rxjs/operators';
import { UsersState, UsersStore } from './users.store';

@Injectable({ providedIn: 'root' })
export class UsersQuery extends Query<UsersState> {

    constructor(protected store: UsersStore) {
        super(store);
    }

    isAuthenticated$ = this.select(q => q.loginResponse !== null);


}
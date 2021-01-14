import { Injectable } from '@angular/core';
import { combineLatest, Observable, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { UsersQuery } from 'src/app/users/state/users.query';

@Injectable({
  providedIn: 'root'
})
export class MenuItemsService {
  visibleMenuItems$: Observable<MenuItem[]> = this.query.select(q => q.loginResponse).pipe(switchMap(u => {
    if (u === null) {
      return of([]);
    }
    const result: MenuItem[] = [
      {
        text: 'Dashboard',
        path: '/directories/all',
        icon: 'home'
      },
      {
        text: 'Categories',
        icon: 'bulletlist',
        path: '/categories/all'
      }
    ];
    console.log("user", u);
    if (u.isAdmin) {
      result.push({
        text: 'Manager',
        icon: 'folder',
        items: [
          {
            text: 'Register',
            icon: 'group',
            path: '/users/register'
          }
        ]
      })
    }
    return of(result);
  }))
  constructor(private query: UsersQuery) { }

}
export class MenuItem {
  text: string;
  icon?: string;
  path?: string;
  items?: MenuItem[];
}

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { UsersQuery } from './state/users.query';
import { UsersHttpService } from './users-http.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private query: UsersQuery, private router: Router) {

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> {

    return this.query.isAuthenticated$.pipe(map(a => {
      if (a === false) {
        this.router.navigate(['login'], {
          // queryParamsHandling: null,
          // queryParams: {
          //   returnUrl: state.url
          // }
        });
        return false;
      }
      return true;
    }))

  }

}

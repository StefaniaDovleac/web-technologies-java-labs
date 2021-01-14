import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { UsersService } from './state/users.service';
import { UsersQuery } from './state/users.query';
;

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
    constructor(private authService: UsersService, private query: UsersQuery) { }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authReq = req.clone({
            setHeaders: {
                Authorization: `Bearer ` + this.authService.token
            }
        });
        console.log("interceptor");

        return next.handle(authReq).pipe(
            tap(
                () => { },
                err => {
                    if ((err.status === 401 || err.status === 0 || err.status === 504)) {
                        this.authService.logout();
                    }
                })
        );

    }
}

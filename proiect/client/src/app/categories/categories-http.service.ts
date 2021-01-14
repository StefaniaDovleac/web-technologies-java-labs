import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { UsersService } from '../users/state/users.service';

@Injectable({
  providedIn: 'root'
})
export class CategoriesHttpService {
  categoriesUrl: string

  constructor(private httpClient: HttpClient, private toastrService: ToastrService, private userService: UsersService) {
    this.categoriesUrl = 'http://localhost:8080/categories/'
  }


  public getBy(id: number): Observable<CategoryDTO> {
    try {
      let queryParams = new HttpParams();
      queryParams = queryParams.set('id', id.toString());
      return this.httpClient.get<CategoryDTO>(this.categoriesUrl + "getBy/", {
        params: queryParams,
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi incarcat categoria")
    }
  }

  public getAll(): Observable<CategoryDTO[]> {

    try {
      const result = this.httpClient.get<CategoryDTO[]>(this.categoriesUrl + "all", {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })

      return result;
    } catch {
      this.toastrService.error("Nu a putut fi incarcat categoriile")
    }
  }

  public create(category: CategoryDTO) {
    try {
      return this.httpClient.post<CategoryDTO>(this.categoriesUrl + "create", category, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi salvat categoria")
    }
  }

  public update(category: CategoryDTO) {
    try {
      return this.httpClient.put<CategoryDTO>(this.categoriesUrl + "update", category, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi editat categoria")
    }
  }

  public delete(id: number): Observable<boolean> {
    console.log("del", id);
    try {
      return this.httpClient.delete<boolean>(this.categoriesUrl + "delete/" + id, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi sters categoria")
    }
  }
}


export class CategoryDTO {
  id: number = 0;
  name: string;
  description: string;
}
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { UsersService } from '../users/state/users.service';

@Injectable({
  providedIn: 'root'
})
export class DirectoriesHttpService {
  directoriesUrl: string;
  constructor(private httpClient: HttpClient, private toastrService: ToastrService, private userService: UsersService) {
    this.directoriesUrl = 'http://localhost:8080/directories/'

  }

  public getBy(id: number): Observable<DirectoryDTO> {
    try {
      return this.httpClient.get<DirectoryDTO>(this.directoriesUrl + id, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi incarcat directorul-ul")
    }
  }

  public getAll(): Observable<DirectoryDTO[]> {

    try {
      const result = this.httpClient.get<DirectoryDTO[]>(this.directoriesUrl + "all", {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })

      return result;
    } catch {
      this.toastrService.error("Nu a putut fi incarcat directoarele")
    }
  }

  public create(directory: DirectoryDTO) {
    try {
      return this.httpClient.post<DirectoryDTO>(this.directoriesUrl + "create", directory, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi salvat directorul")
    }
  }

  public update(directory: DirectoryDTO) {
    try {
      console.log("update", directory);
      return this.httpClient.put<DirectoryDTO>(this.directoriesUrl + "update", directory, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi editat directorul")
    }
  }

  public delete(id: number) {
    console.log("del", id);
    try {
      return this.httpClient.delete<void>(this.directoriesUrl + "delete/" + id, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi sters directorul")
    }
  }
}

export class DirectoryDTO {
  id: number;
  title: string
  parentId?: number;
  createdBy: number;
  createdOn: Date;
  lastModifiedBy: number;
  lastModifiedOn: Date;
  categoryId: number;
}

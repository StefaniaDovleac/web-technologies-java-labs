import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { UsersService } from '../users/state/users.service';

@Injectable({
  providedIn: 'root'
})
export class FilesHttpService {
  filesUrl: string;
  constructor(private httpClient: HttpClient, private toastrService: ToastrService, private userService: UsersService) {
    this.filesUrl = 'http://localhost:8080/files/'
  }

  public getBy(id: number): Observable<FileDTO> {
    try {
      return this.httpClient.get<FileDTO>(this.filesUrl + id, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi incarcat fisierul-ul")
    }
  }

  public getAll(): Observable<FileDTO[]> {

    try {
      return this.httpClient.get<FileDTO[]>(this.filesUrl + "all", {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu au putut fi incarcate fisierele")
    }
  }

  public update(file: FileDTO) {
    try {
      return this.httpClient.put<FileDTO>(this.filesUrl + "update", file, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi editat fisierul")
    }
  }

  public create(directory: FileDTO) {
    try {
      return this.httpClient.post<FileDTO>(this.filesUrl + "create", directory, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi creat directorul")
    }
  }

  public delete(id: number) {
    try {
      return this.httpClient.delete<void>(this.filesUrl + "delete/" + id, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.userService?.token
        })
      })
    } catch {
      this.toastrService.error("Nu a putut fi sters fisierul")
    }
  }

}

export class FileDTO {
  id: number;
  title: string
  parentId: number;
  createdBy: number;
  createdOn: Date;
  lastModifiedBy: number;
  lastModifiedOn: Date;
  size: number;
  fileUri: string;
  content: string;
  isPublic: boolean;
}


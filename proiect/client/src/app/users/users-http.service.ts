import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UsersHttpService {
  private usersUrl: string
  constructor(private httpClient: HttpClient, private toastrService: ToastrService,
    private router: Router) {
    this.usersUrl = 'http://localhost:8080/users/'
  }

  public getById(id: number): Observable<UserDTO> {
    try {
      this.toastrService.success("test");
      return this.httpClient.get<UserDTO>(this.usersUrl + id)
    } catch {
      this.toastrService.error("Nu a putut fi incarcat user-ul")
      return null;
    }
  }

  public login(loginData: LoginRequestDTO): Observable<LoginResponseDTO> {
    try {
      return this.httpClient.post<LoginResponseDTO>("http://localhost:8080/login", loginData)
    } catch {
      this.toastrService.error("Nu a putut fi incarcat user-ul")
      return null;
    }
  }

  public async register(registerData: RegisterDataDTO) {

  }

  public update() {

  }

  public delete() {

  }
}

export class UserDTO {
  id: number;
  userName: string;
  email: string;
  isAdmin: boolean;
}

export class RegisterDataDTO {
  userName: string;
  email: string;
  isAdmin: boolean;
  password: string;
  confirmedPassword: string;
}

export class LoginRequestDTO {
  userName: string;
  password: string;
}

export class LoginResponseDTO {
  userName: string;
  token: string;
  isAdmin: boolean;
}
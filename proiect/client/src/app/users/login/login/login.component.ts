import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { takeWhile } from 'rxjs/operators';
import { UsersQuery } from '../../state/users.query';
import { UsersService } from '../../state/users.service';
import { UsersHttpService } from '../../users-http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements AfterViewInit, OnDestroy {

  form: FormGroup
  loading$ = this.query.selectLoading();
  isDestroyed: boolean;
  constructor(private usersService: UsersService,
    private fb: FormBuilder, private query: UsersQuery) {
    this.form = this.fb.group({
      userName: null,
      password: null,
    })
  }

  ngAfterViewInit() {
    this.form.valueChanges.pipe(takeWhile(_ => !this.isDestroyed)).subscribe(v => {
      this.usersService.updateLoginData(v);
    });
  }

  onLogin() {
    this.usersService.login();
  }

  ngOnDestroy() {
    this.isDestroyed = true;
  }

}

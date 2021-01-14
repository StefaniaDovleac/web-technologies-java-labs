import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { takeWhile } from 'rxjs/operators';
import { UsersQuery } from '../state/users.query';
import { UsersService } from '../state/users.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements AfterViewInit, OnDestroy {
  formData: any = {};

  form: FormGroup;
  loading: boolean;
  passwordPattern: any = '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}';
  isDestroyed: boolean;
  loading$ = this.query.selectLoading();
  passwordComparison = () => {
    return this.form.value.password;
  };

  constructor(private fb: FormBuilder, private usersService: UsersService, private query: UsersQuery) {
    this.form = this.fb.group({
      username: null,
      email: null,
      password: null,
      confirmPassword: null,
    })
  }

  ngAfterViewInit() {
    this.form.valueChanges.pipe(takeWhile(_ => !this.isDestroyed)).subscribe(v => {
      this.usersService.updateRegisterData(v);
    });
  }

  onRegister() {
    this.usersService.register();
  }

  ngOnDestroy() {
    this.isDestroyed = true;
  }

}

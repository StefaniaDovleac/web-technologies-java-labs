import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../state/users.service';
import { UsersHttpService } from '../../users-http.service';

@Component({
  selector: 'app-logout',
  template: ''
})
export class LogoutComponent implements OnInit {

  constructor(private service: UsersService) { }

  ngOnInit() {
    this.service.logout();
  }

}

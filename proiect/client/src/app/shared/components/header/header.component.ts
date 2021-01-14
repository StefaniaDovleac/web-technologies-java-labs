import { Component, NgModule, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthService } from '../../services';
import { UserPanelModule } from '../user-panel/user-panel.component';
import { DxButtonModule } from 'devextreme-angular/ui/button';
import { DxToolbarModule } from 'devextreme-angular/ui/toolbar';
import { UsersHttpService } from 'src/app/users/users-http.service';
import { UsersService } from 'src/app/users/state/users.service';
import { UsersQuery } from 'src/app/users/state/users.query';

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent {
  @Output()
  menuToggle = new EventEmitter<boolean>();

  @Input()
  menuToggleEnabled = false;

  @Input()
  title: string;

  user$ = this.query.select(q => q.loginResponse);

  userMenuItems = [{
    text: 'Profile',
    icon: 'user'
  }, {
    text: 'Logout',
    icon: 'runner',
    onClick: () => {
      this.usersService.logout();
    }
  }];

  constructor(private usersService: UsersService, private query: UsersQuery) { }

  toggleMenu = () => {
    this.menuToggle.emit();
  }
}

@NgModule({
  imports: [
    CommonModule,
    DxButtonModule,
    UserPanelModule,
    DxToolbarModule
  ],
  declarations: [HeaderComponent],
  exports: [HeaderComponent]
})
export class HeaderModule { }

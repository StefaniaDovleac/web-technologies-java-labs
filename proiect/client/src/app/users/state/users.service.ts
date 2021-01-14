import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { confirm, alert } from 'devextreme/ui/dialog';
import { arrayAdd, arrayRemove, arrayToggle, arrayUpdate, arrayUpsert } from '@datorama/akita';
import { UsersStore } from './users.store';
import { LoginRequestDTO, LoginResponseDTO, RegisterDataDTO, UserDTO, UsersHttpService } from '../users-http.service';
import { Router } from '@angular/router';
declare let Camera: any;

@Injectable({ providedIn: 'root' })
export class UsersService {
    loginResult: LoginResponseDTO;
    token: string;

    constructor(private store: UsersStore,
        private toastrService: ToastrService,
        private userHttpService: UsersHttpService,
        private router: Router) {
    }

    async initialize(entityId: number, entityTypeId: number) {
        try {
            this.store.setLoading(true);
        } finally {
            this.store.getValue();
        }
    }

    public updateLoginData(loginData: LoginRequestDTO) {
        this.store.update({
            loginData: {
                ...this.store.getValue().loginData,
                ...loginData
            }
        })
    }

    public async login() {
        try {
            this.store.setLoading(true);
            const loginData = this.store.getValue().loginData;
            // const result = this.userHttpService.login(loginData);
            const result = await this.userHttpService.login(loginData).toPromise();
            if (result) {
                this.token = result.token;
                // this.loginResult.isAdmin = true;
                this.store.update({
                    loginResponse: result,
                });
                this.router.navigate(['directories', 'all']);
            }
        }
        finally {
            this.store.setLoading(false);
        }
    }

    public async logout() {
        await this.router.navigate(['login']);
        this.reset(); // ??
    }

    updateRegisterData(registerData: RegisterDataDTO) {
        this.store.update({
            registerData: {
                ...this.store.getValue().registerData,
                ...registerData
            }
        })
    }

    public async register() {
        try {
            this.store.setLoading(true);
            const registerData = this.store.getValue().registerData;
            console.log("register", registerData);
            const result = this.userHttpService.register(registerData);
            if (result) {
                this.toastrService.success('New account created!')
                this.store.update({
                    registerData: null
                });
            }
        }
        finally {
            this.store.setLoading(false);
        }
    }


    reset() {
        this.store.reset();
    }
}


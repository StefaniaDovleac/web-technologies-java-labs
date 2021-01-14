import { Injectable } from '@angular/core';
import { Store, StoreConfig } from '@datorama/akita';
import { LoginRequestDTO, LoginResponseDTO, RegisterDataDTO, UserDTO } from '../users-http.service';

export interface UsersState {
    loginData: LoginRequestDTO;
    user: UserDTO;
    registerData: RegisterDataDTO;
    loginResponse: LoginResponseDTO
}

export function createInitialState(): UsersState {
    return {
        loginData: null,
        user: null,
        registerData: null,
        loginResponse: null,
    };
}


@Injectable({ providedIn: 'root' })
@StoreConfig({
    name: 'users',
    resettable: true
})
export class UsersStore extends Store<UsersState> {

    constructor() {
        super(createInitialState());
    }

}

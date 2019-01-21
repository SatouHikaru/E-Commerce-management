import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor() { }

    /**
     * Remove Local Storage
     * 
     * @since 02/01/2019
     */
    removeLocalStorage() {
        localStorage.setItem('isLoggedIn', "false");
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('avatar');
    }

}
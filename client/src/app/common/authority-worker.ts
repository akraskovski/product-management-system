import {SecurityService} from "../security/security.service";
import {keys} from "../constants/keys";

export class AuthorityWorker {

    public static getCurrentUser() {
        return JSON.parse(localStorage.getItem(keys.USER_KEY));
    }

    public canWorkWithUser():boolean {
        return SecurityService.hasAuthority("ROLE_ADMIN");
    }

    public canWorkWithProduct(): boolean {
        return SecurityService.hasAuthority("ROLE_ADMIN");
    }

    public canWorkWithStock(): boolean {
        return SecurityService.hasAuthority("ROLE_ADMIN") || SecurityService.hasAuthority("ROLE_STOCK_MANAGER");
    }

    public canWorkWithStore(): boolean {
        return SecurityService.hasAuthority("ROLE_ADMIN") || SecurityService.hasAuthority("ROLE_STORE_MANAGER");
    }

    public isLoggedIn(): boolean {
        return AuthorityWorker.getCurrentUser()
    }
}
import {keys} from "../constants/keys";
import {User} from "../model/user";

export class AuthorityWorker {

    public static getCurrentUser(): User {
        return JSON.parse(localStorage.getItem(keys.USER_KEY));
    }

    public canWorkWithUser(): boolean {
        return AuthorityWorker.componentElementAccess("ROLE_ADMIN");
    }

    public canWorkWithProduct(): boolean {
        return AuthorityWorker.componentElementAccess("ROLE_ADMIN");
    }

    public canWorkWithStock(): boolean {
        return AuthorityWorker.componentElementAccess("ROLE_ADMIN") || AuthorityWorker.componentElementAccess("ROLE_STOCK_MANAGER");
    }

    public canWorkWithStore(): boolean {
        return AuthorityWorker.componentElementAccess("ROLE_ADMIN") || AuthorityWorker.componentElementAccess("ROLE_STORE_MANAGER");
    }

    static componentElementAccess(authority: string): boolean {
        const user: User = AuthorityWorker.getCurrentUser();
        if (user != null) {
            return user.authority.name === authority;
        }
    }
}
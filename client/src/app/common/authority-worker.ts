import {keys} from "../constants/keys";
import {User} from "../model/user";

export class AuthorityWorker {

    public static getCurrentUser() {
        return JSON.parse(localStorage.getItem(keys.USER_KEY));
    }

    public canWorkWithUser(): boolean {
        return this.componentElementAccess("ROLE_ADMIN");
    }

    public canWorkWithProduct(): boolean {
        return this.componentElementAccess("ROLE_ADMIN");
    }

    public canWorkWithStock(): boolean {
        return this.componentElementAccess("ROLE_ADMIN") || this.componentElementAccess("ROLE_STOCK_MANAGER");
    }

    public canWorkWithStore(): boolean {
        return this.componentElementAccess("ROLE_ADMIN") || this.componentElementAccess("ROLE_STORE_MANAGER");
    }

    private componentElementAccess(authority: string): boolean {
        const user: User = AuthorityWorker.getCurrentUser();
        if (user != null)
            for (let currentAuthority of user.authorities) {
                if (currentAuthority.name === authority)
                    return true;
            }
        return false;
    }
}
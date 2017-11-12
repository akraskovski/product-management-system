import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router} from "@angular/router";
import {User} from "../model/user";
import {AuthorityWorker} from "../common/authority-worker";
import {Authority} from "../model/authority";

@Injectable()
export class SecurityService implements CanActivate {
    constructor(private router: Router) {
        this.router = router;
    }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const authorities = route.data["roles"] as Array<string>;
        const user: User = AuthorityWorker.getCurrentUser();
        return authorities.length > 0 && user ? this.checkAuthorities(authorities, user.authorities) : false;
    }

    static containsAuthority(array: any[], obj: any): boolean {
        let index = array.length;
        while (index--) {
            if (array[index] === obj)
                return true;
        }
        return false;
    }

    private checkAuthorities(availableAuthorityList: string[], currentAuthorityList: Authority[]): boolean {
        let flag = false;
        currentAuthorityList.forEach(currentAuthority => {
            if (SecurityService.containsAuthority(availableAuthorityList, currentAuthority.name))
                flag = true;
        });
        return flag;
    }
}
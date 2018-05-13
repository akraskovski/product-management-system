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
        return authorities.length > 0 && user ? SecurityService.containsAuthority(authorities, user.authority) : false;
    }

    private static containsAuthority(array: string[], authority: Authority): boolean {
        let index = array.length;
        while (index--) {
            if (array[index] === authority.name)
                return true;
        }
        return false;
    }

}
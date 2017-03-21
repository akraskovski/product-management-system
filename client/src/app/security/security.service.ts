import {Injectable} from "@angular/core";
import {CanActivate, Router, ActivatedRouteSnapshot} from "@angular/router";
import {User} from "../model/user";
import {Authority} from "../model/authority";
import {AuthorizationService} from "../authorization/authorization.service";

@Injectable()
export class SecurityService implements CanActivate {

    private router: Router;

    constructor(router: Router) {
        this.router = router;
    }

    canActivate(route: ActivatedRouteSnapshot) {
        const authorities = route.data["roles"] as Array<string>;
        const user: User = AuthorizationService.getCurrentUser();
        if (authorities.length > 0 && user) {
            if (SecurityService.checkAuthorities(authorities, user.authorities))
                return true;
        }
        alert('You don\'t have permissions!');
        this.router.navigate(['/']);
        return false;
    }

    private static checkAuthorities(availableAuthorityList: string[], currentAuthorityList: Authority[]): boolean {
        for (let availableAuthority = 0; availableAuthority < availableAuthorityList.length; availableAuthority++) {
            for (let userAuthority = 0; userAuthority < currentAuthorityList.length; userAuthority++)
                if (availableAuthorityList[availableAuthority] == currentAuthorityList[userAuthority].name) {
                    return true;
                }
        }
        return false;
    }

    public static isAdmin(): boolean {
        const user: User = AuthorizationService.getCurrentUser();
        if (user != null)
            for (let authority of user.authorities) {
                if (authority.name === "ROLE_ADMIN")
                    return true;
            }
        return false;
    }
}
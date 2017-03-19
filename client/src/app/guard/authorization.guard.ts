import {Injectable} from "@angular/core";
import {CanActivate, Router, ActivatedRouteSnapshot} from "@angular/router";
import {User} from "../model/user";
import {Authority} from "../model/authority";
import {AuthorizationService} from "../authorization/authorization.service";

@Injectable()
export class AuthorizationGuard implements CanActivate {

    private router: Router;

    constructor(router: Router) {
        this.router = router;
    }

    canActivate(route: ActivatedRouteSnapshot) {
        const roles = route.data["roles"] as Array<string>;
        const user: User = AuthorizationService.getCurrentUser();
        if (roles.length > 0 && user) {
            if (AuthorizationGuard.checkRoles(roles, user.authorities))
                return true;
        }
        alert('You don\'t have permissions!');
        this.router.navigate(['/home']);
        return false;
    }

    private static checkRoles(avialableRoleList: string[], currentRoleList: Authority[]): boolean {
        for (let avialableRole = 0; avialableRole < avialableRoleList.length; avialableRole++) {
            for (let userRole = 0; userRole < currentRoleList.length; userRole++)
                if (avialableRoleList[avialableRole] == currentRoleList[userRole].name) {
                    return true;
                }
        }
        return false;
    }
}
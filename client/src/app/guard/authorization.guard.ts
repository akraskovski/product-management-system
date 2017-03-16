import {Injectable} from "@angular/core";
import {CanActivate, Router, ActivatedRouteSnapshot} from "@angular/router";
import {environment} from "../constants/environment";
import {User} from "../model/user";

@Injectable()
export class AuthorizationGuard implements CanActivate {

    private router: Router;

    constructor(router: Router) {
        this.router = router;
    }

    canActivate(route: ActivatedRouteSnapshot) {
        const roles = route.data["roles"] as Array<string>;
        const user: User = this.getUser();
        if (roles.length > 0 && user) {
            return this.checkRoles(roles, user.roles);
        } else {
            alert('You dont have permissions!');
            this.router.navigate(['/']);
            return false;
        }
    }

    private getUser(): User {
        const user = localStorage.getItem(environment.USER_KEY);
        if (user) {
            return JSON.parse(user);
        } else {
            return null;
        }
    }

    private checkRoles(avialableRoleList: string[], currentRoleList: string[]): boolean {
        for (let avialableRole = 0; avialableRole < avialableRoleList.length; avialableRole++) {
            for (let userRole = 0; userRole < currentRoleList.length; userRole++)
                if (avialableRoleList[avialableRole] == currentRoleList[userRole]) {
                    return true;
                }
        }
        return false;
    }
}
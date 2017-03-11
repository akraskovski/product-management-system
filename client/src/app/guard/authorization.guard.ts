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
        let roles = route.data["roles"] as Array<string>;
        let user: User;
        if (localStorage.getItem(environment.USER_KEY) && roles) {
            user = JSON.parse(localStorage.getItem(environment.USER_KEY));
        } else {
            return false;
        }

        for (let avialableRole = 0; avialableRole < roles.length; avialableRole++) {
            for (let userRole = 0; userRole < user.roles.length; userRole++)
                if (roles[avialableRole] == user.roles[userRole]) {
                    return true;
                }
        }

        alert('You dont have permissions!');
        this.router.navigate(['/']);
        return false;
    }
}
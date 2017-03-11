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
        let user: User = JSON.parse(localStorage.getItem(environment.USER_KEY));

        for(let i = 0; i < roles.length; i++){
            if(roles[i] == user.roles[0] ){
                return true;
            }
        }
        alert('You dont have permissions!');
        this.router.navigate(['/']);
        return false;
    }
}
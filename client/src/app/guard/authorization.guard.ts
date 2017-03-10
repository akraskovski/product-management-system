import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {environment} from "../constants/environment";

@Injectable()
export class AuthorizationGuard implements CanActivate {

    private router: Router;

    constructor(router: Router) {
        this.router = router;
    }


    canActivate() {
        if (localStorage.getItem(environment.USER_KEY)) {
            return true;
        }
        alert('Please login first!');
        this.router.navigate(['/login']);
        return false;
    }
}
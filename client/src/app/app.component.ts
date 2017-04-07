import {Component} from "@angular/core";
import {AuthorizationService} from "./authorization/authorization.service";
import {Router} from "@angular/router";
import {AuthorityWorker} from "./common/authority-worker";
import {User} from "./model/user";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent extends AuthorityWorker {

    welcomeName: string;

    constructor(private router: Router, private authorizationService: AuthorizationService) {
        super();
    }

    public isLoggedIn(): boolean {
        const user: User = AuthorityWorker.getCurrentUser();
        if (user) {
            this.welcomeName = user.username;
            return true;
        }
        return false;
    }

    logout(): void {
        this.welcomeName = null;
        this.authorizationService.logout();
        this.router.navigate(["/login"]);
    }
}

import {Component} from "@angular/core";
import {AuthorizationService} from "./authorization/authorization.service";
import {Router} from "@angular/router";
import {AuthorityWorker} from "./common/authority-worker";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent extends AuthorityWorker{

    constructor(private router: Router, private authorizationService: AuthorizationService) {
        super();
    }

    logout(): void {
        if (this.isLoggedIn()) {
            this.authorizationService.logout();
            this.router.navigate(["/"]);
        }
    }
}

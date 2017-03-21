import {Component} from '@angular/core';
import {AuthorizationService} from "./authorization/authorization.service";
import {Router} from "@angular/router";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {

    constructor(private router: Router, private authorizationService: AuthorizationService) {

    }

    isLoggedIn(): boolean {
        return AuthorizationService.getCurrentUser();
    }

    logout(): void {
        if (this.isLoggedIn()) {
            this.authorizationService.logout();
            this.router.navigate(["/"]);
        }
    }
}

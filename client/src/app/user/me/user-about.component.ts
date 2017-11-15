import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";
import {Router} from "@angular/router";

@Component({
    selector: 'user-about-component',
    templateUrl: 'user-about.component.html'
})
export class UserAboutComponent implements OnInit{
    user: User;

    constructor(private userService: UserService, private router: Router) {
        this.user = new User();
    }

    ngOnInit(): void {
        this.userService.getCurrentUser()
            .subscribe(userDto => this.user = userDto,
                error => this.logError(error));
    }

    logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
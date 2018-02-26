import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'user-about-component',
    templateUrl: 'user-about.component.html'
})
export class UserAboutComponent implements OnInit {
    user: User;

    constructor(private notificationService: NotificationService,
                private userService: UserService) {
        this.user = new User();
    }

    ngOnInit(): void {
        this.userService.getCurrentUser()
            .subscribe(userDto => this.user = userDto,
                error => this.notificationService.error(error.message));

    }
}
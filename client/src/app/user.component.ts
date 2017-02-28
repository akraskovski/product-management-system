import {Component} from "@angular/core";
import {User} from "./user";
import {UserService} from "./user.service";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html'
})
export class UserComponent {
    findUser: User;
    userName: string;

    constructor(private userService: UserService) {
    }

    getUserByName(): void {
        this.userService.getUserByName(this.userName)
            .then(user => {
                this.findUser = user;
                alert(`
                    ID: ${this.findUser.id}\n
                    NAME: ${this.findUser.name}
                `);
            });
    }
}
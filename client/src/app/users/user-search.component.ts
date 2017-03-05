import {Component} from "@angular/core";
import {User} from "../model/user";
import {UserService} from "./user.service";

@Component({
    selector: 'user-search-component',
    templateUrl: './user-search.component.html'
})
export class UserSearchComponent {
    findUser: User;
    inputText: string;

    constructor(private userService: UserService) {
    }

    loadUserByUsername(): void {
        this.userService.getUserByUsername(this.inputText)
            .then(user => {
                this.findUser = user;
                alert(`
                    ID: ${this.findUser.id}\n
                    USERNAME: ${this.findUser.username}\n
                    PASSWORD: ${this.findUser.password}
                `);
            });
    }
}
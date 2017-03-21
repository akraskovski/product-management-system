import {Component} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";

@Component({
    selector: 'user-search-component',
    templateUrl: 'user-search.component.html'
})
export class UserSearchComponent {
    findUser: User;
    inputText: string;

    constructor(private userService: UserService) {
    }

    loadUserByUsername(): void {
        this.userService.loadByUsername(this.inputText)
            .subscribe(user => {
                this.findUser = user;
                alert(`
                    USERNAME: ${this.findUser.username}\n
                    PASSWORD: ${this.findUser.password}
                `);
            });
    }
}
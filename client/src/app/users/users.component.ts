import {Component, OnInit} from "@angular/core";
import {User} from "./model/user";
import {UserService} from "./user.service";

@Component({
    selector: 'user-search-component',
    templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit{
    users: User[];

    constructor(private userService: UserService) { }

    ngOnInit(): void {
        this.getAllUsers();
    }

    getAllUsers(): void {
        this.userService.getUsers()
            .then(usersFromService => this.users = usersFromService);
    }
}
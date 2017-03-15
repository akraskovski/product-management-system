import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";

@Component({
    selector: 'all-users-component',
    templateUrl: './all-users.component.html'
})
export class AllUsersComponent implements OnInit{
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
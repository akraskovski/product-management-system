import {Component, OnInit} from '@angular/core';
import '../style/app.css';
import {UserService} from "./user.service";
import {User} from "./user";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
    title: string = 'Angular 2 Component';
    users: User[];

    constructor(private dataService: UserService) { }

    ngOnInit(): void {
        this.getAllUsers();
    }

    getAllUsers(): void {
        this.dataService.getUsers()
            .then(usersFromService => this.users = usersFromService);
    }
}

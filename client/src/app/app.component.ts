import {Component, OnInit} from '@angular/core';
import '../style/app.css';
import {DataService} from "./data.service";
import {User} from "./user";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

    title: string = 'Angular 2 Component';
    users: User[];

    constructor(private dataService: DataService) { }

    ngOnInit(): void {
        this.getData();
    }

    getData(): void {
        this.dataService.getUsers().then(usersFromService => this.users = usersFromService);
    }
}

import {Component, OnInit} from "@angular/core";
import {User} from "../model/user";
import {CommonService} from "../common/common.service";
import {Router} from "@angular/router";
import {api} from "../constants/api";

@Component({
    selector: 'user-component',
    templateUrl: './user.component.html',
    styleUrls: ['user.component.css']
})
export class UserComponent implements OnInit {
    userList: User[];
    selectedUser: User;

    constructor(private userService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.userService.loadAll(api.USER)
            .subscribe(
                userList => this.userList = userList,
                err => this.logError(err)
            );
    }

    onSelect(user: User): void {
        this.selectedUser = user;
    }

    onEdit(id: number): void {
        this.router.navigate(['user']).then(() => id && this.router.navigate(['user/user-update', id]));
    }

    logError(error: Error) {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
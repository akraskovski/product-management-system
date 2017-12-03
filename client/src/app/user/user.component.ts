import {Component, OnInit} from "@angular/core";
import {User} from "../model/user";
import {CommonService} from "../common/common.service";
import {Router} from "@angular/router";
import {api} from "../constants/api";
import {UserService} from "./user.service";

@Component({
    selector: 'user-component',
    templateUrl: 'user.component.html',
    styleUrls: ['user.component.css']
})
export class UserComponent implements OnInit {
    userList: User[];
    selectedUser: User;
    user: User;

    constructor(private commonService: CommonService, private userService: UserService, private router: Router) {
        this.userList = [];
        this.user = new User();
    }

    ngOnInit(): void {
        this.userService.getCurrentUser()
            .subscribe(userDto =>
                    this.user = userDto,
                error => this.logError(error)
            );
        this.loadData();
    }

    private loadData(): void {
        this.commonService.loadAll(api.USER)
            .subscribe(
                userList => {
                    userList.forEach(user => {
                        if (user.id != this.user.id) {
                            this.userList.push(user);
                        }
                    });
                }, err => this.logError(err)
            );
    }

    onSelect(user: User): void {
        this.selectedUser = user;
    }

    onEdit(id: number): void {
        this.router.navigate(['user']).then(() => id && this.router.navigate(['user/user-update', id]));
    }

    logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
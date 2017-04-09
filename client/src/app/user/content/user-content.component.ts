import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";

@Component({
    selector: 'user-content-component',
    templateUrl: 'user-content.component.html',
    styleUrls: ['user-content.component.css']
})
export class UserContentComponent implements OnInit {
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

    onDelete(identifier: number): void {
        this.userService.remove(api.USER, identifier)
            .subscribe(
                () => this.loadData(),
                err => this.logError(err));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['user/user-update', identifier]);
    }

    logError(err) {
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}
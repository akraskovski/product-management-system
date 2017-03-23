import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {SecurityService} from "../../security/security.service";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";
import {Router} from "@angular/router";

@Component({
    selector: 'user-content-component',
    templateUrl: 'user-content.component.html'
})
export class UserContentComponent implements OnInit {
    userList: User[];

    constructor(private userService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.userService.loadAll(environment.USER_URL)
            .subscribe(userList => this.userList = userList);
    }

    onDelete(identifier: number): void {
        this.userService.remove(environment.USER_URL, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
            error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['user/user-update', identifier]);
    }

    isAdmin(): boolean {
        return SecurityService.isAdmin();
    }
}
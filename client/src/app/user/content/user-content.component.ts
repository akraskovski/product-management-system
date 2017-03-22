import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";
import {SecurityService} from "../../security/security.service";

@Component({
    selector: 'user-content-component',
    templateUrl: 'user-content.component.html'
})
export class UserContentComponent implements OnInit{
    userList: User[];

    constructor(private userService: UserService) { }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.userService.loadAll()
            .subscribe(userList => this.userList = userList);
    }

    onDelete(identifier: number): void {
        this.userService.remove(identifier)
            .subscribe(result => {
                if (result === true) {
                    this.loadData();
                } else {
                    alert("Error!");
                }
            }, error => alert(error));
    }

    isAdmin(): boolean{
        return SecurityService.isAdmin();
    }
}
import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";
import {CommonComponent} from "../../common/common.component";

@Component({
    selector: 'user-content-component',
    templateUrl: 'user-content.component.html'
})
export class UserContentComponent extends CommonComponent implements OnInit {
    userList: User[];

    constructor(private userService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.userService.loadAll(api.USER)
            .subscribe(userList => this.userList = userList);
    }

    onDelete(identifier: number): void {
        this.userService.remove(api.USER, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['user/user-update', identifier]);
    }
}
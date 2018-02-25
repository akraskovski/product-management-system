import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../model/user";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'user-create-component',
    templateUrl: 'user-create.component.html'
})
export class UserCreateComponent {
    userForm: FormGroup;
    availableAuthorities: Authority[];
    selectedAuthorities: Authority[];
    loading: boolean;

    constructor(private userService: CommonService,
                private router: Router,
                private notificationService: NotificationService) {
        this.availableAuthorities = [];
        this.selectedAuthorities = [];
        this.loading = false;
    }

    ngOnInit(): void {
        this.loadAuthorities();
        this.createEmptyForm();
    }

    private loadAuthorities(): void {
        this.userService.loadAll(api.AUTHORITY)
            .subscribe(
                availableAuthorities => this.availableAuthorities = availableAuthorities,
                error => this.logError(error));
    }

    private createEmptyForm(): void {
        this.userForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', [Validators.required, Validators.pattern(regex.PASSWORD)]),
            firstName: new FormControl(),
            lastName: new FormControl(),
            email: new FormControl('', Validators.pattern(regex.EMAIL)),
            phone: new FormControl('', Validators.pattern(regex.PHONE_NUMBER)),
            avatar: new FormControl()
        });
    }

    onSubmit(): void {
        this.loading = true;
        let user: User = new User();
        user.username = this.userForm.value.username;
        user.password = this.userForm.value.password;
        user.authorities = this.selectedAuthorities;
        user.firstName = this.userForm.value.firstName;
        user.lastName = this.userForm.value.lastName;
        user.email = this.userForm.value.email;
        user.phone = this.userForm.value.phone;
        this.userService.create(api.USER, user)
            .subscribe(
                () => this.router.navigate(['/']).then(() => this.router.navigate(['user'])),
                err => this.logError(err));
    }

    addAuthorityToSelected(authority: Authority): void {
        this.availableAuthorities.splice(this.availableAuthorities.indexOf(authority), 1);
        this.selectedAuthorities.push(authority);
    }

    deleteAuthorityFromSelected(authority: Authority): void {
        this.selectedAuthorities.splice(this.selectedAuthorities.indexOf(authority), 1);
        this.availableAuthorities.push(authority);
    }

    logError(error: Error): void {
        this.loading = false;
        this.notificationService.error(error.message);
    }
}
import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../model/user";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {NotificationService} from "../../notification/notification.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
    selector: 'user-create-component',
    templateUrl: 'user-create.component.html'
})
export class UserCreateComponent {
    userForm: FormGroup;
    availableAuthorities: Authority[];
    selectedAuthority: Authority;
    loading: boolean;

    constructor(private userService: CommonService,
                private router: Router,
                private notificationService: NotificationService) {
        this.loading = false;
    }

    ngOnInit(): void {
        this.loadAuthorities();
        this.createEmptyForm();
    }

    private loadAuthorities(): void {
        this.userService.loadAll(api.AUTHORITY)
            .subscribe(
                availableAuthorities => {
                    this.availableAuthorities = availableAuthorities;
                    if (this.availableAuthorities)
                        this.selectedAuthority = this.availableAuthorities[0];
                },
                error => this.logError(error));
    }

    private createEmptyForm(): void {
        this.userForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', [Validators.required, Validators.pattern(regex.PASSWORD)]),
            firstName: new FormControl('',Validators.required),
            lastName: new FormControl('',Validators.required),
            email: new FormControl('', [Validators.required, Validators.pattern(regex.EMAIL)]),
            phone: new FormControl('', [Validators.required, Validators.pattern(regex.PHONE_NUMBER)]),
            avatar: new FormControl()
        });
    }

    onSubmit(): void {
        this.loading = true;
        let user: User = new User();
        user.username = this.userForm.value.username;
        user.password = this.userForm.value.password;
        user.authority = this.selectedAuthority;
        user.firstName = this.userForm.value.firstName;
        user.lastName = this.userForm.value.lastName;
        user.email = this.userForm.value.email;
        user.phone = this.userForm.value.phone;
        this.userService.create(api.USER, user)
            .subscribe(
                () => {
                    this.notificationService.success("User successfully created");
                    this.router.navigate(['/']).then(() => this.router.navigate(['user']));
                },
                (err: HttpErrorResponse) => this.logError(err));
    }

    logError(error: Error): void {
        this.loading = false;
        this.notificationService.error(error.message);
    }
}
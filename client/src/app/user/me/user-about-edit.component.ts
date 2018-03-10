import {Component, OnInit} from "@angular/core";
import {User} from "../../model/user";
import {UserService} from "../user.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {regex} from "../../constants/regex";
import {api} from "../../constants/api";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'user-about-edit-component',
    templateUrl: 'user-about-edit.component.html'
})
export class UserAboutEditComponent implements OnInit {
    user: User;
    userForm: FormGroup;

    constructor(private notificationService: NotificationService,
                private userService: UserService,
                private router: Router) {
        this.user = new User();
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.userForm = new FormGroup({
            firstName: new FormControl(),
            lastName: new FormControl(),
            email: new FormControl('', Validators.pattern(regex.EMAIL)),
            phone: new FormControl('', Validators.pattern(regex.PHONE_NUMBER))
        });
    }

    private fillForm(): void {
        this.userService.getCurrentUser()
            .subscribe(
                (userDto: User) => {
                    this.user = userDto;
                    this.userForm.setValue({
                        firstName: this.user.firstName,
                        lastName: this.user.lastName,
                        email: this.user.email,
                        phone: this.user.phone
                    });
                },
                error => this.notificationService.error(error.message));
    }

    onSubmit(): void {
        this.user.firstName = this.userForm.value.firstName;
        this.user.lastName = this.userForm.value.lastName;
        this.user.email = this.userForm.value.email;
        this.user.phone = this.userForm.value.phone;
        this.userService.update(api.USER, this.user)
            .subscribe(
                () => {
                    this.notificationService.success("Profile info successfully updated");
                    this.router.navigate(['/']).then(() => this.router.navigate(['me']))
                },
                error => this.notificationService.error(error.message));
    }
}
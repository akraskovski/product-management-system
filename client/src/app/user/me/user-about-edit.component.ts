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
            username: new FormControl('', Validators.required),
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
                    //todo: fix ebanoe govno
                    if (this.user.username)
                        this.userForm.setValue({username: this.user.username});
                    if (this.user.email)
                        this.userForm.setValue({email: this.user.email});
                    if (this.user.phone)
                        this.userForm.setValue({phone: this.user.phone});
                    if (this.user.firstName)
                        this.userForm.setValue({firstName: this.user.firstName});
                    if (this.user.lastName)
                        this.userForm.setValue({lastName: this.user.lastName});
                },
                error => this.notificationService.error(error.message));
    }

    onSubmit(): void {
        this.user.username = this.userForm.value.username;
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
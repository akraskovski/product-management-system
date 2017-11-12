import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {AuthorizationService} from "./authorization.service";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Component({
    selector: 'authorization-component',
    templateUrl: 'authorization.component.html'
})
export class AuthorizationComponent implements OnInit {
    loginForm: FormGroup;
    loading: boolean;
    error: string;

    constructor(private authorizationService: AuthorizationService, private router: Router) {
        this.loading = false;
        this.error = '';
    }

    ngOnInit(): void {
        AuthorizationService.logout();
        this.createEmptyForm();
    }

    private createEmptyForm(): void {
        this.loginForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
    }

    onSubmit(): void {
        this.loading = true;
        let user: User = new User();
        user.username = this.loginForm.value.username;
        user.password = this.loginForm.value.password;
        this.authorizationService.login(user)
            .subscribe(result => {
                    if (result === true) {
                        this.router.navigate(['/'])
                    } else {
                        this.handleError(new Error("User in null"));
                    }
                },
                error => this.handleError(error)
            );
    }

    onClose(): void {
        this.loading = false;
        this.router.navigate(['/']);
    }

    private handleError(error: Error): void {
        this.error = <any> error;
        this.loading = false;
        this.createEmptyForm();
    }
}
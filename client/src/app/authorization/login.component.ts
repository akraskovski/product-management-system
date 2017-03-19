import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {LoginService} from "./login.service";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Component({
    selector: 'login-component',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    loading: boolean = false;
    error: string = '';

    constructor(private loginService: LoginService, private router: Router) {
    }

    ngOnInit(): void {
        this.loginForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
        this.loginService.logout();
    }

    onSubmit() {
        this.loading = true;
        this.loginService.login(new User(this.loginForm.value.username, this.loginForm.value.password))
            .subscribe(
                result => {
                    result && this.router.navigate(['/']);
                },
                error => {
                    this.error = <any>error;
                    this.loading = false;
                }
            );
    }

}
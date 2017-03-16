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
    user: FormGroup;
    loading = false;
    error = '';

    constructor(private loginService: LoginService, private router: Router) {
    }

    ngOnInit(): void {
        this.user = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
        this.loginService.logout();
    }

    onSubmit() {
        this.authenticate();
    }

    private authenticate(): void {
        this.loading = true;
        this.loginService.login(new User(this.user.value.username, this.user.value.password))
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['/']);
                } else {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }
            });
    }

}
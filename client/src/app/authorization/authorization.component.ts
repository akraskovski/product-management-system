import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {AuthorizationService} from "./authorization.service";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Component({
    selector: 'authorization-component',
    templateUrl: './authorization.component.html'
})
export class AuthorizationComponent implements OnInit {
    loginForm: FormGroup;
    loading: boolean = false;
    error: string = '';

    constructor(private authorizationService: AuthorizationService, private router: Router) {
    }

    ngOnInit(): void {
        this.loginForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
        this.authorizationService.logout();
    }

    onSubmit() {
        this.loading = true;
        this.authorizationService.login(new User(this.loginForm.value.username, this.loginForm.value.password))
            .subscribe(
                result => result && this.router.navigate(['/']),
                error => {
                    this.error = <any>error;
                    this.loading = false;
                }
            );
    }

}
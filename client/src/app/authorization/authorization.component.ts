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
    loading: boolean;
    error: string;

    constructor(private authorizationService: AuthorizationService, private router: Router) {
        this.loading = false;
        this.error = '';
    }

    ngOnInit(): void {
        this.authorizationService.logout();
        this.createEmptyForm();
    }

    private createEmptyForm(): void {
        this.loginForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
    }

    onSubmit() {
        this.loading = true;
        this.authorizationService.login(new User(this.loginForm.value.username, this.loginForm.value.password))
            .subscribe(
                result => result && this.router.navigate(['/']),
                error => this.handleError(error)
            );
    }

    onClose() {
        this.loading = false;
        this.router.navigate(['/']);
    }

    private handleError(error: Error): void {
        this.error = <any> error;
        this.loading = false;
        this.createEmptyForm();
    }
}
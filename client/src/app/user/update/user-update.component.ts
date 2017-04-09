import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {CommonFunctions} from "../../common/common-functions";

@Component({
    selector: 'user-update-component',
    templateUrl: './user-update.component.html'
})
export class UserUpdateComponent implements OnInit {
    userForm: FormGroup;
    loading: boolean = false;
    user: User;
    availableAuthorities: Authority[] = [];
    selectedAuthorities: Authority[] = [];

    constructor(private userService: CommonService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.userForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required)
        });
    }

    private fillForm(): void {
        this.userService.loadById(api.USER, this.route.snapshot.params['id'])
            .subscribe(
                (user: User) => {
                    this.user = user;
                    this.selectedAuthorities = this.user.authorities;
                    this.loadAuthorities();
                    this.userForm.setValue({
                        username: this.user.username,
                        password: this.user.password
                    });
                },
                error => this.logError(error));
    }

    private loadAuthorities(): void {
        this.userService.loadAll(api.AUTHORITY)
            .subscribe(
                availableAuthorities => this.availableAuthorities = CommonFunctions.cleanAvailableItems(availableAuthorities, this.selectedAuthorities),
                error => this.logError(error));
    }

    onSubmit(): void {
        this.loading = true;
        this.user.username = this.userForm.value.username;
        this.user.password = this.userForm.value.password;
        this.user.authorities = this.selectedAuthorities;
        this.userService.update(api.USER, this.user)
            .subscribe(
                () => this.router.navigate(['user/user-content']),
                error => this.logError(error));
    }

    addAuthorityToSelected(authority: Authority): void {
        this.availableAuthorities.splice(this.availableAuthorities.indexOf(authority), 1);
        this.selectedAuthorities.push(authority);
    }

    deleteAuthorityFromSelected(authority: Authority): void {
        this.selectedAuthorities.splice(this.selectedAuthorities.indexOf(authority), 1);
        this.availableAuthorities.push(authority);
    }

    logError(err) {
        this.loading = false;
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}
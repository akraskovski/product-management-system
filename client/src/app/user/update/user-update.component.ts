import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";

@Component({
    selector: 'user-update-component',
    templateUrl: './user-update.component.html'
})
export class UserUpdateComponent implements OnInit {
    userForm: FormGroup;
    loading = false;
    user: User;
    availableAuthorities: Authority[] = [];
    selectedAuthorities: Authority[] = [];

    constructor(private userService: CommonService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.userService.loadAll(environment.AUTHORITY_URL)
            .subscribe(availableAuthorities => this.availableAuthorities = availableAuthorities);
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
        this.userService.loadById(environment.USER_URL, this.route.snapshot.params['id'])
            .subscribe(user => {
                this.user = user;
                this.selectedAuthorities = this.user.authorities;
                this.cleanAvailableAuthorities();
                this.userForm.setValue({
                    username: this.user.username,
                    password: this.user.password
                });
            });
    }

    private cleanAvailableAuthorities(): void {
        for (let availableAuthority = 0; availableAuthority < this.availableAuthorities.length; availableAuthority++)
            for (let selectedAuthority = 0; selectedAuthority < this.selectedAuthorities.length; selectedAuthority++)
                if (this.availableAuthorities[availableAuthority].id === this.selectedAuthorities[selectedAuthority].id)
                    this.availableAuthorities.splice(this.availableAuthorities.indexOf(this.availableAuthorities[availableAuthority]), 1);
    }

    onSubmit(): void {
        this.loading = true;
        this.user.username = this.userForm.value.username;
        this.user.password = this.userForm.value.password;
        this.user.authorities = this.selectedAuthorities;
        this.userService.update(environment.USER_URL, this.user)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['user/user-content']);
                } else {
                    this.loading = false;
                    alert("Error!");
                }
            });
    }

    addAuthorityToSelected(authority: Authority): void {
        this.availableAuthorities.splice(this.availableAuthorities.indexOf(authority), 1);
        this.selectedAuthorities.push(authority);
    }

    deleteAuthorityFromSelected(authority: Authority): void {
        this.selectedAuthorities.splice(this.selectedAuthorities.indexOf(authority), 1);
        this.availableAuthorities.push(authority);
    }
}
import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";

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
            .subscribe(user => {
                this.user = user;
                this.selectedAuthorities = this.user.authorities;
                this.loadAuthorities();
                this.userForm.setValue({
                    username: this.user.username,
                    password: this.user.password
                });
            });
    }

    private loadAuthorities(): void {
        this.userService.loadAll(api.AUTHORITY)
            .subscribe(availableAuthorities => this.availableAuthorities = this.cleanAvailableAuthorities(availableAuthorities))
    }

    private cleanAvailableAuthorities(authoritiesList: Authority[]): Authority[] {
        for (let authority = 0; authority < authoritiesList.length; authority++)
            for (let selectedAuthority = 0; selectedAuthority < this.selectedAuthorities.length; selectedAuthority++)
                if (authoritiesList[authority].id === this.selectedAuthorities[selectedAuthority].id)
                    authoritiesList.splice(authoritiesList.indexOf(authoritiesList[authority]), 1);
        return authoritiesList;
    }

    onSubmit(): void {
        this.loading = true;
        this.user.username = this.userForm.value.username;
        this.user.password = this.userForm.value.password;
        this.user.authorities = this.selectedAuthorities;
        this.userService.update(api.USER, this.user)
            .subscribe(result => result ? this.router.navigate(['user/user-content']) : this.errorMsg);
    }

    addAuthorityToSelected(authority: Authority): void {
        this.availableAuthorities.splice(this.availableAuthorities.indexOf(authority), 1);
        this.selectedAuthorities.push(authority);
    }

    deleteAuthorityFromSelected(authority: Authority): void {
        this.selectedAuthorities.splice(this.selectedAuthorities.indexOf(authority), 1);
        this.availableAuthorities.push(authority);
    }

    private errorMsg(): void {
        this.loading = false;
        alert("Error!");
    }
}
import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../model/user";
import {Authority} from "../../model/authority";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";
@Component({
    selector: 'user-create-component',
    templateUrl: 'user-create.component.html'
})
export class UserCreateComponent {
    userForm: FormGroup;
    availableAuthorities: Authority[] = [];
    selectedAuthorities: Authority[] = [];
    loading = false;

    constructor(private userService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.userService.loadAll(environment.AUTHORITY_URL)
            .subscribe(availableAuthorities => this.availableAuthorities = availableAuthorities);
        this.userForm = new FormGroup({
            username: new FormControl('', Validators.required),
            password: new FormControl('', Validators.required),
        });
    }

    onSubmit() {
        this.loading = true;
        let user: User = new User(this.userForm.value.username, this.userForm.value.password);
        user.authorities = this.selectedAuthorities;
        this.userService.create(environment.USER_URL, user)
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
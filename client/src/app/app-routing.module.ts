import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UsersComponent} from "./users/users.component";
import {UserSearchComponent} from "./users/user-search.component";
import {LoginComponent} from "./authorization/login.component";
import {SecuredComponent} from "./secured/secured.component";
import {AuthorizationGuard} from "./guard/authorization.guard";

const routes: Routes = [
    {path: '', redirectTo: '', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'all-users', component: UsersComponent},
    {path: 'search', component: UserSearchComponent},
    {path: 'secured', component: SecuredComponent, canActivate: [AuthorizationGuard], data: { roles: ['ROLE_USER']} }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
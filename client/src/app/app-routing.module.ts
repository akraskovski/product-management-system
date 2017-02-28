import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UsersComponent} from "./users/users.component";
import {UserSearchComponent} from "./users/user-search.component";

const routes: Routes = [
    {path: '', redirectTo: '', pathMatch: 'full'},
    {path: 'all-users', component: UsersComponent},
    {path: 'search', component: UserSearchComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
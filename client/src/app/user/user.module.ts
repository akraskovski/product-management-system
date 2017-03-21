import {NgModule} from "@angular/core";
import {UserSearchComponent} from "./search/user-search.component";
import {AllUsersComponent} from "./all/all-users.component";
import {UserComponent} from "./user.component";
import {UserService} from "./user.service";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        HttpModule,
        CommonModule
    ],
    declarations: [
        UserComponent,
        AllUsersComponent,
        UserSearchComponent
    ],
    providers: [
        UserService
    ]
})
export class UserModule {

}
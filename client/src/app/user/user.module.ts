import {NgModule} from "@angular/core";
import {UserSearchComponent} from "./search/user-search.component";
import {UserComponent} from "./user.component";
import {UserService} from "./user.service";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {UserContentComponent} from "./content/user-content.component";
import {UserCreateComponent} from "./create/user-create.component";
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        HttpModule,
        ReactiveFormsModule,
        CommonModule
    ],
    declarations: [
        UserComponent,
        UserContentComponent,
        UserCreateComponent,
        UserSearchComponent
    ],
    providers: [
        UserService
    ]
})
export class UserModule {
}
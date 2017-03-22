import {NgModule} from "@angular/core";
import {UserComponent} from "./user.component";
import {UserService} from "./user.service";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {UserContentComponent} from "./content/user-content.component";
import {UserCreateComponent} from "./create/user-create.component";
import {UserUpdateComponent} from "./update/user-update.component";
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
        UserUpdateComponent
    ],
    providers: [
        UserService
    ]
})
export class UserModule {
}
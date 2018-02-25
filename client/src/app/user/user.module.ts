import {NgModule} from "@angular/core";
import {UserComponent} from "./user.component";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {UserCreateComponent} from "./create/user-create.component";
import {UserUpdateComponent} from "./update/user-update.component";
import {CommonService} from "../common/common.service";
import {UserRoutingModule} from "./user-routing.module";
import {TranslateModule} from "../translate/translate.module";
import {UserAboutComponent} from "./me/user-about.component";
import {UserService} from "./user.service";
import {UserAboutEditComponent} from "./me/user-about-edit.component";
import {NotificationService} from "../notification/notification.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        HttpModule,
        ReactiveFormsModule,
        CommonModule,
        UserRoutingModule,
        TranslateModule.forRoot()
    ],
    declarations: [
        UserComponent,
        UserCreateComponent,
        UserUpdateComponent,
        UserAboutComponent,
        UserAboutEditComponent
    ],
    providers: [
        UserService,
        CommonService,
        NotificationService
    ]
})
export class UserModule {
}
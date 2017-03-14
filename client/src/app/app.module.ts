import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {UserService} from "./users/user.service";
import {HttpModule} from "@angular/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserSearchComponent} from "./users/user-search.component";
import {UsersComponent} from "./users/users.component";
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from "./authorization/login.component";
import {LoginService} from "./authorization/login.service";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {AuthorizationGuard} from "./guard/authorization.guard";
import {SecuredComponent} from "./secured/secured.component";
import {SecuredService} from "./secured/secured.service";
import {HomeComponent} from "./home/home.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        UsersComponent,
        UserSearchComponent,
        SecuredComponent
    ],
    providers: [
        AuthorizationGuard,
        UserService,
        LoginService,
        SecuredService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {UserService} from "./user/user.service";
import {HttpModule} from "@angular/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserSearchComponent} from "./user/search/user-search.component";
import {AppRoutingModule} from "./app-routing.module";
import {AuthorizationComponent} from "./authorization/authorization.component";
import {AuthorizationService} from "./authorization/authorization.service";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {SecurityService} from "./security/security.service";
import {HomeComponent} from "./home/home.component";
import {ProductComponent} from "./product/product.component";
import {ProductService} from "./product/product.service";
import {ProductContentComponent} from "./product/content/product-content.component";
import {ProductCreateComponent} from "./product/create/product-create.component";
import {ProductSearchComponent} from "./product/search/product-search.component";
import {ProductUpdateComponent} from "./product/update/product-update.component";
import {UserComponent} from "./user/user.component";
import {AllUsersComponent} from "./user/all/all-users.component";

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
        AuthorizationComponent,
        UserComponent,
        AllUsersComponent,
        UserSearchComponent,
        ProductComponent,
        ProductContentComponent,
        ProductCreateComponent,
        ProductSearchComponent,
        ProductUpdateComponent
    ],
    providers: [
        AuthorizationService,
        SecurityService,
        UserService,
        ProductService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

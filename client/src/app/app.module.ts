import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {UserService} from "./user/user.service";
import {HttpModule} from "@angular/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserSearchComponent} from "./user/search/user-search.component";
import {AppRoutingModule} from "./app-routing.module";
import {LoginComponent} from "./authorization/login.component";
import {LoginService} from "./authorization/login.service";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {AuthorizationGuard} from "./guard/authorization.guard";
import {HomeComponent} from "./home/home.component";
import {ProductComponent} from "./product/product.component";
import {ProductService} from "./product/product.service";
import {AllProductsComponent} from "./product/all/all-products.component";
import {AddProductComponent} from "./product/add/add-product.component";
import {ProductSearchComponent} from "./product/search/product-search.component";
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
        LoginComponent,
        UserComponent,
        AllUsersComponent,
        UserSearchComponent,
        ProductComponent,
        AllProductsComponent,
        AddProductComponent,
        ProductSearchComponent
    ],
    providers: [
        AuthorizationGuard,
        UserService,
        LoginService,
        ProductService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

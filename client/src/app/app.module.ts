import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {HttpModule} from "@angular/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import {AuthorizationComponent} from "./authorization/authorization.component";
import {AuthorizationService} from "./authorization/authorization.service";
import {CommonModule, HashLocationStrategy, LocationStrategy} from "@angular/common";
import {RouterModule} from "@angular/router";
import {SecurityService} from "./security/security.service";
import {ProductModule} from "./product/product.module";
import {UserModule} from "./user/user.module";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        UserModule,
        ProductModule,
        AppRoutingModule,
    ],
    declarations: [
        AppComponent,
        AuthorizationComponent,
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        AuthorizationService,
        SecurityService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

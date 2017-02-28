import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {UserService} from "./user.service";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {UserComponent} from "./user.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    declarations: [
        AppComponent,
        UserComponent
    ],
    providers: [UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

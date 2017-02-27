import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {DataService} from "./data.service";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    declarations: [AppComponent],
    providers: [DataService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {StockComponent} from "./stock.component";
import {StockService} from "./stock.service";
import {StockContentComponent} from "./content/stock-content.component";
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
    ],
    declarations: [
        StockComponent,
        StockContentComponent
    ],
    providers: [
        StockService
    ]
})
export class StockModule {
}
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {StockComponent} from "./stock.component";
import {StockContentComponent} from "./content/stock-content.component";
import {StockCreateComponent} from "./create/stock-create.component";
import {CommonService} from "../common/common.service";
import {StockUpdateComponent} from "./update/stock-update.component";
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
        StockComponent,
        StockContentComponent,
        StockCreateComponent,
        StockUpdateComponent
    ],
    providers: [
        CommonService
    ]
})
export class StockModule {
}
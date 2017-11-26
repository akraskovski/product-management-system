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
import {StockRoutingModule} from "./stock-routing.module";
import {TranslateModule} from "../translate/translate.module";
import {StockService} from "./stock.service";
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        HttpModule,
        ReactiveFormsModule,
        CommonModule,
        StockRoutingModule,
        TranslateModule
    ],
    declarations: [
        StockComponent,
        StockContentComponent,
        StockCreateComponent,
        StockUpdateComponent
    ],
    providers: [
        CommonService,
        StockService
    ]
})
export class StockModule {
}
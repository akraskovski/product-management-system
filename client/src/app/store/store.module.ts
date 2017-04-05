import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";
import {CommonService} from "../common/common.service";
import {StoreComponent} from "./store.component";
import {StoreContentComponent} from "./content/store-content.component";
import {StoreCreateComponent} from "./create/store-create.component";
import {StoreUpdateComponent} from "./update/store-update.component";
import {StoreRoutingModule} from "./store-routing.module";
import {StoreDetailsComponent} from "./details/store-details.component";
import {ImageService} from "../image/image.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        HttpModule,
        ReactiveFormsModule,
        CommonModule,
        StoreRoutingModule
    ],
    declarations: [
        StoreComponent,
        StoreContentComponent,
        StoreCreateComponent,
        StoreUpdateComponent,
        StoreDetailsComponent
    ],
    providers: [
        CommonService,
        ImageService
    ]
})
export class StoreModule {
}
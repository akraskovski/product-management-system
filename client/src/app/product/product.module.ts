import {NgModule} from "@angular/core";
import {ProductComponent} from "./product.component";
import {ProductContentComponent} from "./content/product-content.component";
import {ProductCreateComponent} from "./create/product-create.component";
import {ProductSearchComponent} from "./search/product-search.component";
import {ProductUpdateComponent} from "./update/product-update.component";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {CommonService} from "../common/common.service";
import {ProductRoutingModule} from "./product-routing.module";
import {TranslateModule} from "../translate/translate.module";
import {ImageService} from "../common/image.service";
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        CommonModule,
        ReactiveFormsModule,
        RouterModule,
        ProductRoutingModule,
        TranslateModule
    ],
    declarations: [
        ProductComponent,
        ProductContentComponent,
        ProductCreateComponent,
        ProductSearchComponent,
        ProductUpdateComponent
    ],
    providers: [
        CommonService,
        ImageService
    ]
})
export class ProductModule {
}
import {NgModule} from "@angular/core";
import {ProductComponent} from "./product.component";
import {ProductContentComponent} from "./content/product-content.component";
import {ProductCreateComponent} from "./create/product-create.component";
import {ProductSearchComponent} from "./search/product-search.component";
import {ProductUpdateComponent} from "./update/product-update.component";
import {ProductService} from "./product.service";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
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
        ProductComponent,
        ProductContentComponent,
        ProductCreateComponent,
        ProductSearchComponent,
        ProductUpdateComponent
    ],
    providers: [
        ProductService
    ]
})
export class ProductModule {
}
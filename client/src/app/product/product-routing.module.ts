import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SecurityService} from "../security/security.service";
import {ProductComponent} from "./product.component";
import {ProductContentComponent} from "./content/product-content.component";
import {ProductSearchComponent} from "./search/product-search.component";
import {ProductCreateComponent} from "./create/product-create.component";
import {ProductUpdateComponent} from "./update/product-update.component";

export const routes: Routes = [
    {
        path: 'product', component: ProductComponent,
        children: [
            {path: 'product-content', component: ProductContentComponent},
            {path: 'product-search/:keyword', component: ProductSearchComponent},
            {
                path: 'product-create',
                component: ProductCreateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN']}
            },
            {
                path: 'product-update/:id',
                component: ProductUpdateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN']}
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class ProductRoutingModule {
}
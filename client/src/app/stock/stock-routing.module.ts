import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SecurityService} from "../security/security.service";
import {StockComponent} from "./stock.component";
import {StockContentComponent} from "./content/stock-content.component";
import {StockCreateComponent} from "./create/stock-create.component";
import {StockUpdateComponent} from "./update/stock-update.component";

export const routes: Routes = [
    {
        path: 'stock', redirectTo: 'stock/stock-content', pathMatch: 'full'
    },
    {
        path: 'stock',
        component: StockComponent,
        canActivate: [SecurityService],
        data: {
            roles: ['ROLE_ADMIN', 'ROLE_STOCK_MANAGER']
        },
        children: [
            {
                path: 'stock-content',
                component: StockContentComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STOCK_MANAGER"]}
            },
            {
                path: 'stock-create',
                component: StockCreateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STOCK_MANAGER"]}
            },
            {
                path: 'stock-update/:id',
                component: StockUpdateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STOCK_MANAGER"]}
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class StockRoutingModule {
}
import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {AuthorizationComponent} from "./authorization/authorization.component";
import {SecurityService} from "./security/security.service";
import {ProductComponent} from "./product/product.component";
import {ProductContentComponent} from "./product/content/product-content.component";
import {ProductCreateComponent} from "./product/create/product-create.component";
import {ProductSearchComponent} from "./product/search/product-search.component";
import {ProductUpdateComponent} from "./product/update/product-update.component";
import {UserComponent} from "./user/user.component";
import {UserContentComponent} from "./user/content/user-content.component";
import {UserCreateComponent} from "./user/create/user-create.component";
import {UserUpdateComponent} from "./user/update/user-update.component";
import {StockComponent} from "./stock/stock.component";
import {StockContentComponent} from "./stock/content/stock-content.component";
import {StockCreateComponent} from "./stock/create/stock-create.component";
import {StockUpdateComponent} from "./stock/update/stock-update.component";

const routes: Routes = [
    {path: '', redirectTo: '', pathMatch: 'full'},
    {path: 'login', component: AuthorizationComponent},
    {
        path: 'user', component: UserComponent, canActivate: [SecurityService], data: {roles: ['ROLE_ADMIN']},
        children: [
            {
                path: 'user-content',
                component: UserContentComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN']}
            },
            {
                path: 'user-create',
                component: UserCreateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN']}
            },
            {
                path: 'user-update/:id',
                component: UserUpdateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN']}
            }
        ]
    },
    {
        path: 'product', component: ProductComponent,
        children: [
            {path: 'product-content', component: ProductContentComponent},
            {path: 'product-search', component: ProductSearchComponent},
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
    },
    {
        path: 'stock', component: StockComponent, canActivate: [SecurityService], data: {
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
export class AppRoutingModule {
}
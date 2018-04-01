import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {StoreComponent} from "./store.component";
import {SecurityService} from "../security/security.service";
import {StoreContentComponent} from "./content/store-content.component";
import {StoreCreateComponent} from "./create/store-create.component";
import {StoreUpdateComponent} from "./update/store-update.component";
import {StoreDetailsComponent} from "./details/store-details.component";
import {StoreManageComponent} from "./manage/store-manage.component";

export const routes: Routes = [
    {
        path: 'store', redirectTo: 'store/store-content', pathMatch: 'full'
    },
    {
        path: 'store', component: StoreComponent,
        children: [
            {
                path: 'store-content',
                component: StoreContentComponent
            },
            {
                path: 'store-create',
                component: StoreCreateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STORE_MANAGER"]}
            },
            {
                path: 'store-update/:id',
                component: StoreUpdateComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STORE_MANAGER"]}
            },
            {
                path: 'store-details/:id',
                component: StoreDetailsComponent
            },
            {
                path: 'store-manage/:id',
                component: StoreManageComponent,
                canActivate: [SecurityService],
                data: {roles: ['ROLE_ADMIN', "ROLE_STORE_MANAGER"]}
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class StoreRoutingModule {
}
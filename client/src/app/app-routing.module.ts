import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UserSearchComponent} from "./user/search/user-search.component";
import {AuthorizationComponent} from "./authorization/authorization.component";
import {SecurityService} from "./security/security.service";
import {HomeComponent} from "./home/home.component";
import {ProductComponent} from "./product/product.component";
import {ProductContentComponent} from "./product/content/product-content.component";
import {ProductCreateComponent} from "./product/create/product-create.component";
import {ProductSearchComponent} from "./product/search/product-search.component";
import {ProductUpdateComponent} from "./product/update/product-update.component";
import {UserComponent} from "./user/user.component";
import {AllUsersComponent} from "./user/all/all-users.component";

const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: AuthorizationComponent},
    {path: 'product', component: ProductComponent},
    {path: 'product-content', component: ProductContentComponent},
    {path: 'product-create', component: ProductCreateComponent, canActivate: [SecurityService], data: { roles: ['ROLE_ADMIN']}},
    {path: 'product-search', component: ProductSearchComponent},
    {path: 'product-update', component: ProductUpdateComponent, canActivate: [SecurityService], data: { roles: ['ROLE_ADMIN']}},
    {path: 'user', component: UserComponent},
    {path: 'all-users', component: AllUsersComponent},
    {path: 'user-search', component: UserSearchComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UserSearchComponent} from "./user/search/user-search.component";
import {LoginComponent} from "./authorization/login.component";
import {SecuredComponent} from "./secured/secured.component";
import {AuthorizationGuard} from "./guard/authorization.guard";
import {HomeComponent} from "./home/home.component";
import {ProductComponent} from "./product/product.component";
import {AllProductsComponent} from "./product/all/all-products.component";
import {AddProductComponent} from "./product/add/add-product.component";
import {ProductSearchComponent} from "./product/search/product-search.component";
import {UserComponent} from "./user/user.component";
import {AllUsersComponent} from "./user/all/all-users.component";

const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'product', component: ProductComponent},
    {path: 'all-products', component: AllProductsComponent},
    {path: 'add-product', component: AddProductComponent, canActivate: [AuthorizationGuard], data: { roles: ['ROLE_ADMIN']}},
    {path: 'product-search', component: ProductSearchComponent},
    {path: 'user', component: UserComponent},
    {path: 'all-users', component: AllUsersComponent},
    {path: 'user-search', component: UserSearchComponent},
    {path: 'secured', component: SecuredComponent, canActivate: [AuthorizationGuard], data: { roles: ['ROLE_ADMIN']} }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {UsersComponent} from "./user/users.component";
import {UserSearchComponent} from "./user/user-search.component";
import {LoginComponent} from "./authorization/login.component";
import {SecuredComponent} from "./secured/secured.component";
import {AuthorizationGuard} from "./guard/authorization.guard";
import {HomeComponent} from "./home/home.component";
import {ProductComponent} from "./product/product.component";
import {AllProductsComponent} from "./product/all/all-products.component";

const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'product', component: ProductComponent},
    {path: 'all-products', component: AllProductsComponent},
    {path: 'all-users', component: UsersComponent},
    {path: 'search', component: UserSearchComponent},
    {path: 'secured', component: SecuredComponent, canActivate: [AuthorizationGuard], data: { roles: ['ROLE_ADMIN']} }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
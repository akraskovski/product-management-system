import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthorizationComponent} from "./authorization/authorization.component";

const routes: Routes = [
    {path: '', redirectTo: '', pathMatch: 'full'},
    {path: 'login', component: AuthorizationComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SecurityService} from "../security/security.service";
import {UserComponent} from "./user.component";
import {UserContentComponent} from "./content/user-content.component";
import {UserCreateComponent} from "./create/user-create.component";
import {UserUpdateComponent} from "./update/user-update.component";

export const routes: Routes = [
    {
        path: 'user', redirectTo: 'user/user-content', pathMatch: 'full'
    },
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
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {
}

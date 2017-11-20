import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SecurityService} from "../security/security.service";
import {UserComponent} from "./user.component";
import {UserCreateComponent} from "./create/user-create.component";
import {UserUpdateComponent} from "./update/user-update.component";
import {UserAboutComponent} from "./me/user-about.component";
import {UserAboutEditComponent} from "./me/user-about-edit.component";

export const routes: Routes = [
    {
        path: 'user', component: UserComponent, canActivate: [SecurityService], data: {roles: ['ROLE_ADMIN']},
        children: [
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
        path: 'me', component: UserAboutComponent
    },
    {
        path: 'me-edit', component: UserAboutEditComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {
}

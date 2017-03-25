import {SecurityService} from "../security/security.service";

export class CommonComponent {

    isAdmin(): boolean {
        return SecurityService.isAdmin();
    }
}
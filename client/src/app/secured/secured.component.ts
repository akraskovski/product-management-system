import {Component} from "@angular/core";
import {SecuredService} from "./secured.service";

@Component({
    selector: 'secured-component',
    templateUrl: './secured.component.html'
})
export class SecuredComponent {

    constructor(private securedService: SecuredService) {
    }

    onSubmit() {
        this.securedService.getMessage()
            .subscribe(responce => alert(responce));
    }
}
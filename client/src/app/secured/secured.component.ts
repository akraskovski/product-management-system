import {Component} from "@angular/core";
import {SecuredService} from "./secured.service";

@Component({
    selector: 'secured-component',
    templateUrl: './secured.component.html'
})
export class SecuredComponent {

    private data;

    constructor(private securedService: SecuredService) {
    }

    onSubmit() {
        this.securedService.getMessage()
            .subscribe(responce => {
                this.data = responce;
                alert(this.data);
            });
    }
}
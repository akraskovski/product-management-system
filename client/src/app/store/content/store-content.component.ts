import {Component} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {environment} from "../../constants/environment";
import {SecurityService} from "../../security/security.service";
@Component({
    selector: 'store-content-component',
    templateUrl: './store-content.component.html'
})
export class StoreContentComponent{
    storeList: Store[];

    constructor(private storeService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.storeService.loadAll(environment.STORE_URL)
            .subscribe(storeList => this.storeList = storeList);
    }

    onDelete(identifier: number): void {
        this.storeService.remove(environment.STORE_URL, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['store/store-update', identifier]);
    }

    isAdmin(): boolean {
        return SecurityService.isAdmin();
    }
}
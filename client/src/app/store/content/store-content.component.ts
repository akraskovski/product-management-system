import {Component} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {CommonComponent} from "../../common/common.component";

@Component({
    selector: 'store-content-component',
    templateUrl: './store-content.component.html'
})
export class StoreContentComponent extends CommonComponent {
    storeList: Store[];

    constructor(private storeService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.storeService.loadAll(api.STORE)
            .subscribe(storeList => this.storeList = storeList);
    }

    onDelete(identifier: number): void {
        this.storeService.remove(api.STORE, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['store/store-update', identifier]);
    }
}
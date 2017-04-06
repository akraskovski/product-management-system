import {Component} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {AuthorityWorker} from "../../common/authority-worker";

@Component({
    selector: 'store-content-component',
    templateUrl: './store-content.component.html'
})
export class StoreContentComponent extends AuthorityWorker {
    storeList: Store[];

    constructor(private storeService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.storeService.loadAllUnauthorized(api.STORE)
            .subscribe(
                storeList => this.storeList = storeList,
                error => this.logError(error));
    }

    onDelete(id: number): void {
        this.storeService.remove(api.STORE, id)
            .subscribe(
                () => this.loadData(),
                error => this.logError(error));
    }

    onEdit(id: number): void {
        id && this.router.navigate(['store/store-update', id]);
    }

    onDetails(id: number): void {
        id && this.router.navigate(['store/store-details', id]);
    }

    logError(error: Error) {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
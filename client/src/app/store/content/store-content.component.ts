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
                err => this.logError(err));
    }

    onDelete(identifier: number): void {
        this.storeService.remove(api.STORE, identifier)
            .subscribe(
                () => this.loadData(),
                err => this.logError(err));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['store/store-update', identifier]);
    }

    onDetails(identifier: number): void {
        identifier && this.router.navigate(['store/store-details', identifier]);
    }

    logError(err) {
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}
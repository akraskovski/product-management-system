import {Component} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {AuthorityWorker} from "../../common/authority-worker";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'store-content-component',
    templateUrl: 'store-content.component.html'
})
export class StoreContentComponent extends AuthorityWorker {
    storeList: Store[];

    constructor(private storeService: CommonService,
                private router: Router,
                private notificationService: NotificationService) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData(): void {
        this.storeService.loadAllUnauthorized(api.STORE)
            .subscribe(
                storeList => this.storeList = storeList,
                error => this.logError(error));
    }

    onDelete(id: string): void {
        this.storeService.remove(api.STORE, id)
            .subscribe(
                () => this.loadData(),
                error => this.logError(error));
    }

    onEdit(id: string): void {
        id && this.router.navigate(['store/store-update', id]);
    }

    onDetails(id: string): void {
        id && this.router.navigate(['store/store-details', id]);
    }

    onManageContent(id: string): void {
        id && this.router.navigate(['store/store-manage', id]);
    }

    logError(error: Error): void {
        this.notificationService.error('There was an error: ' + error.message ? error.message : error.toString());
    }
}
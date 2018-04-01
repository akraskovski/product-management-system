import {Component, OnInit} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {ActivatedRoute} from "@angular/router";
import {CommonFunctions} from "../../common/common-functions";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'store-details-component',
    templateUrl: 'store-details.component.html'
})
export class StoreDetailsComponent implements OnInit {
    getImageUrl = CommonFunctions.getImageUrl;
    selectedStore: Store;

    constructor(private storeService: CommonService,
                private route: ActivatedRoute,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData(): void {
        this.storeService.loadByIdUnauthorized(api.STORE, this.route.snapshot.params['id'])
            .subscribe(
                store => this.selectedStore = store,
                err => this.logError(err));
    }

    private logError(error: Error): void {
        this.notificationService.error(error.message ? error.message : error.toString());
    }
}
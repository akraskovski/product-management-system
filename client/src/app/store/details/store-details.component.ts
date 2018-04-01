import {Component, OnInit} from "@angular/core";
import {Store} from "../../model/store";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {ActivatedRoute} from "@angular/router";
import {CommonFunctions} from "../../common/common-functions";
import {NotificationService} from "../../notification/notification.service";
import {StoreService} from "../store.service";
import {Stock} from "../../model/stock";

@Component({
    selector: 'store-details-component',
    templateUrl: 'store-details.component.html'
})
export class StoreDetailsComponent implements OnInit {
    getImageUrl = CommonFunctions.getImageUrl;
    selectedStore: Store;
    stockList: Stock[];

    constructor(private commonService: CommonService,
                private storeService: StoreService,
                private route: ActivatedRoute,
                private notificationService: NotificationService) {
        this.stockList = [];
    }


    ngOnInit(): void {
        this.loadData();
    }

    private loadData(): void {
        this.commonService.loadByIdUnauthorized(api.STORE, this.route.snapshot.params['id'])
            .subscribe(
                store => {
                    this.selectedStore = store;
                    this.storeService.getStocks(this.selectedStore.id)
                        .subscribe(
                            stockList => this.stockList = stockList,
                            err => this.logError(err));
                },
                err => this.logError(err));
    }

    private logError(error: Error): void {
        this.notificationService.error(error.message ? error.message : error.toString());
    }
}
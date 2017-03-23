import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";
@Component({
    selector: 'stock-content-component',
    templateUrl: './stock-content.component.html'
})
export class StockContentComponent implements OnInit {

    stockList: Stock[];

    constructor(private stockService: CommonService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.stockService.loadAll(environment.STOCK_URL)
            .subscribe(stockList => this.stockList = stockList);
    }
}
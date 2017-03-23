import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {StockService} from "../stock.service";
@Component({
    selector: 'stock-content-component',
    templateUrl: './stock-content.component.html'
})
export class StockContentComponent implements OnInit{

    stockList: Stock[];

    constructor(private stockService: StockService) { }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.stockService.loadAll()
            .subscribe(stockList => this.stockList = stockList);
    }
}
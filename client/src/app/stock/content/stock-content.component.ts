import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";
@Component({
    selector: 'stock-content-component',
    templateUrl: './stock-content.component.html'
})
export class StockContentComponent implements OnInit {
    stockList: Stock[];

    constructor(private stockService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData(): void {
        this.stockService.loadAll(api.STOCK)
            .subscribe(
                stockList => this.stockList = stockList,
                error => this.logError(error));
    }

    onDelete(id: number): void {
        this.stockService.remove(api.STOCK, id)
            .subscribe(
                () => this.loadData(),
                error => this.logError(error));
    }

    onEdit(id: number): void {
        id && this.router.navigate(['stock/stock-update', id]);
    }

    logError(error): void {
        console.error('There was an error: ' + + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
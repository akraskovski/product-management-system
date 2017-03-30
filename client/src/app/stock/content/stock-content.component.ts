import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";
import {AuthorityWorker} from "../../common/authority-worker";
@Component({
    selector: 'stock-content-component',
    templateUrl: './stock-content.component.html'
})
export class StockContentComponent extends AuthorityWorker implements OnInit {
    stockList: Stock[];

    constructor(private stockService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.stockService.loadAll(api.STOCK)
            .subscribe(
                stockList => this.stockList = stockList,
                err => this.logError(err));
    }

    onDelete(identifier: number): void {
        this.stockService.remove(api.STOCK, identifier)
            .subscribe(
                () => this.loadData(),
                err => this.logError(err));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['stock/stock-update', identifier]);
    }

    logError(err) {
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}
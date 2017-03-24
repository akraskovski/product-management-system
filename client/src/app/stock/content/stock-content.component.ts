import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";
import {Router} from "@angular/router";
import {SecurityService} from "../../security/security.service";
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

    private loadData() {
        this.stockService.loadAll(environment.STOCK_URL)
            .subscribe(stockList => this.stockList = stockList);
    }

    onDelete(identifier: number): void {
        this.stockService.remove(environment.STOCK_URL, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['stock/stock-update', identifier]);
    }

    isAdmin(): boolean {
        return SecurityService.isAdmin();
    }
}
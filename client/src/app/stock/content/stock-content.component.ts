import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";
import {CommonComponent} from "../../common/common.component";
@Component({
    selector: 'stock-content-component',
    templateUrl: './stock-content.component.html'
})
export class StockContentComponent extends CommonComponent implements OnInit {

    stockList: Stock[];

    constructor(private stockService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.stockService.loadAll(api.STOCK)
            .subscribe(stockList => this.stockList = stockList);
    }

    onDelete(identifier: number): void {
        this.stockService.remove(api.STOCK, identifier)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['stock/stock-update', identifier]);
    }
}
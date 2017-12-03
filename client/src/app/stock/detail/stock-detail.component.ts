import {Component, OnInit} from "@angular/core";
import {Product} from "../../model/product";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {StockService} from "../stock.service";
import {ActivatedRoute, Router} from "@angular/router";
import {api} from "../../constants/api";
@Component({
    selector: 'stock-detail-component',
    templateUrl: 'stock-detail.component.html'
})
export class StockDetailComponent implements OnInit {

    stock: Stock;
    availableProducts: Product[] = [];
    selectedProducts: Product[] = [];

    constructor(private commonService: CommonService, private stockService: StockService, private router: Router, private route: ActivatedRoute) {
        this.stock = new Stock();
        this.availableProducts = [];
        this.selectedProducts = [];
    }

    ngOnInit(): void {
        this.availableProducts = [];
        this.selectedProducts = [];
        this.load();
    }

    private load(): void {
        this.commonService.loadById(api.STOCK, this.route.snapshot.params['id'])
            .subscribe(
                stock => {
                    this.stock = stock;
                    this.stockService.getStockProducts(this.stock.id).subscribe(
                        products => this.selectedProducts = products
                    );
                },
                error => this.logError(error));
    }

    private logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
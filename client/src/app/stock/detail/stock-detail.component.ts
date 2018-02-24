import {Component, OnInit} from "@angular/core";
import {Product} from "../../model/product";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {StockService} from "../stock.service";
import {ActivatedRoute} from "@angular/router";
import {api} from "../../constants/api";
import {StockItem} from "../../model/stock-item";

@Component({
    selector: 'stock-detail-component',
    templateUrl: 'stock-detail.component.html',
    styleUrls: ['stock-detail-component.css']
})
export class StockDetailComponent implements OnInit {
    stock: Stock;
    selectedProducts: StockItem[];
    productList: Product[];
    filteredItems: Product[];
    pageSize: number = 10;
    pages: number;
    currentIndex: number;
    pagesIndex: number[];
    pageStart: number;

    constructor(private commonService: CommonService,
                private stockService: StockService,
                private route: ActivatedRoute) {
        this.stock = new Stock();
        this.selectedProducts = [];
        this.productList = [];
        this.filteredItems = [];
        this.pagesIndex = [];
    }

    ngOnInit(): void {
        this.load();
    }

    private load(): void {
        this.commonService.loadById(api.STOCK, this.route.snapshot.params['id'])
            .subscribe(
                stock => {
                    this.stock = stock;
                    this.stockService.getStockProducts(this.stock.id).subscribe(
                        (products: StockItem[]) => this.selectedProducts = products
                    );
                },
                error => this.logError(error)
            );
    }

    public loadPossibleProducts(): void {
        this.commonService.loadAll(api.PRODUCT)
            .subscribe(
                productList => {
                    this.productList = this.filteredItems = productList;
                    this.createPagination();
                },
                err => this.logError(err)
            );
    }

    createPagination(): void {
        this.currentIndex = 1;
        this.pageStart = 1;
        this.pages = parseInt("" + this.filteredItems.length / this.pageSize);
        if (this.filteredItems.length % this.pageSize != 0) {
            this.pages++;
        }
        this.refreshItems();
    }

    refreshItems(): void {
        this.productList = this.filteredItems.slice((this.currentIndex - 1) * this.pageSize, (this.currentIndex) * this.pageSize);
        this.pagesIndex = this.fillArray();
    }

    fillArray(): any {
        let obj = [];
        for (let index = this.pageStart; index < this.pageStart + this.pages; index++) {
            obj.push(index);
        }
        return obj;
    }

    prevPage(): void {
        if (this.currentIndex > 1) {
            this.currentIndex--;
        }
        if (this.currentIndex < this.pageStart) {
            this.pageStart = this.currentIndex;
        }
        this.refreshItems();
    }

    nextPage(): void {
        if (this.currentIndex < this.pages) {
            this.currentIndex++;
        }
        if (this.currentIndex >= (this.pageStart + this.pages)) {
            this.pageStart = this.currentIndex - this.pages + 1;
        }
        this.refreshItems();
    }

    setPage(index: number): void {
        this.currentIndex = index;
        this.refreshItems();
    }

    onAddProduct(product: Product): void {
        this.stockService.addProductToStock(this.stock.id, product.id)
            .subscribe(() => this.load(),
                error => this.logError(error));
    }

    private logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        window.location.reload();
    }
}
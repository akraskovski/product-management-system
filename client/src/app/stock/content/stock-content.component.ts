import {Component, OnInit} from "@angular/core";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {Router} from "@angular/router";
import {AuthorityWorker} from "../../common/authority-worker";
@Component({
    selector: 'stock-content-component',
    templateUrl: 'stock-content.component.html'
})
export class StockContentComponent extends AuthorityWorker implements OnInit {
    stockList: Stock[];
    filteredItems: Stock[];
    pageSize: number = 10;
    pages: number;
    currentIndex: number;
    pagesIndex: number[];
    pageStart: number;

    constructor(private stockService: CommonService, private router: Router) {
        super();
        this.stockList = [];
        this.filteredItems = [];
        this.pagesIndex = [];
    }

    ngOnInit(): void {
        this.load();
    }

    private load(): void {
        this.stockService.loadAll(api.STOCK)
            .subscribe(
                stockList => {
                    this.stockList = this.filteredItems = stockList;
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
        this.stockList = this.filteredItems.slice((this.currentIndex - 1) * this.pageSize, (this.currentIndex) * this.pageSize);
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

    onDelete(id: string): void {
        this.stockService.remove(api.STOCK, id)
            .subscribe(
                () => this.load(),
                error => this.logError(error));
    }

    onEdit(id: number): void {
        id && this.router.navigate(['stock/stock-update', id]);
    }

    onDetail(id: number): void {
        id && this.router.navigate(['stock/detail', id]);
    }

    onChangePageSize(): void {
        this.createPagination();
    }

    logError(error): void {
        console.error('There was an error: ' + + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
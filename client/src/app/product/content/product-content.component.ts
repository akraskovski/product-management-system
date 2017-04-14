import {Component, OnInit} from "@angular/core";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {AuthorityWorker} from "../../common/authority-worker";

@Component({
    selector: 'product-content-component',
    templateUrl: 'product-content.component.html'
})
export class ProductContentComponent extends AuthorityWorker implements OnInit {
    productList: Product[];
    filteredItems: Product[];
    pages: number = 4;
    pageSize: number = 5;
    pageNumber: number = 0;
    currentIndex: number = 1;
    pagesIndex: Array<number>;
    pageStart: number = 1;

    constructor(private productService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.load();
    }

    createPagination() {
        this.currentIndex = 1;
        this.pageStart = 1;
        this.pages = 4;
        this.pageNumber = parseInt("" + (this.filteredItems.length / this.pageSize));
        if (this.filteredItems.length % this.pageSize != 0) {
            this.pageNumber++;
        }
        if (this.pageNumber < this.pages) {
            this.pages = this.pageNumber;
        }
        this.refreshItems();
    }

    refreshItems() {
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

    prevPage() {
        if (this.currentIndex > 1) {
            this.currentIndex--;
        }
        if (this.currentIndex < this.pageStart) {
            this.pageStart = this.currentIndex;
        }
        this.refreshItems();
    }

    nextPage() {
        console.log("next page clicked");
        if (this.currentIndex < this.pageNumber) {
            this.currentIndex++;
        }
        if (this.currentIndex >= (this.pageStart + this.pages)) {
            this.pageStart = this.currentIndex - this.pages + 1;
        }
        this.refreshItems();
    }

    setPage(index: number) {
        this.currentIndex = index;
        this.refreshItems();
    }

    private load(): void {
        this.productService.loadAll(api.PRODUCT)
            .subscribe(
                productList => {
                    this.productList = productList;
                    this.filteredItems = productList;
                    this.createPagination();
                },
                err => this.logError(err)
            );
    }

    onDelete(id: number): void {
        this.productService.remove(api.PRODUCT, id)
            .subscribe(
                () => this.load(),
                error => this.logError(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['product/product-update', identifier]);
    }

    logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
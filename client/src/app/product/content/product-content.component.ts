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
    pageSize: number = 2;
    pageNumber: number = 0;
    currentIndex: number = 1;
    pagesIndex: number[];
    pageStart: number = 1;

    constructor(private productService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.load();
    }

    private load(): void {
        this.productService.loadAll(api.PRODUCT)
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
        this.pageNumber = parseInt("" + this.filteredItems.length / this.pageSize);
        if (this.filteredItems.length % this.pageSize != 0) {
            this.pageNumber++;
        }
        this.refreshItems();
    }

    refreshItems(): void {
        this.productList = this.filteredItems.slice((this.currentIndex - 1) * this.pageSize, (this.currentIndex) * this.pageSize);
        this.pagesIndex = this.fillArray();
    }

    fillArray(): any {
        let obj = [];
        for (let index = this.pageStart; index < this.pageStart + this.pageNumber; index++) {
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
        if (this.currentIndex < this.pageNumber) {
            this.currentIndex++;
        }
        if (this.currentIndex >= (this.pageStart + this.pageNumber)) {
            this.pageStart = this.currentIndex - this.pageNumber + 1;
        }
        this.refreshItems();
    }

    setPage(index: number): void {
        this.currentIndex = index;
        this.refreshItems();
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

    onChangePageSize(): void {
        this.createPagination();
    }

    logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
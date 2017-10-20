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
    selectedProduct: Product;
    filteredItems: Product[];
    pageSize: number = 10;
    pages: number;
    currentIndex: number;
    pagesIndex: number[];
    pageStart: number;

    constructor(private productService: CommonService, private router: Router) {
        super();
        this.productList = [];
        this.filteredItems = [];
        this.pagesIndex = [];
    }

    ngOnInit(): void {
        this.load();
    }

    getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
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

    onDelete(id: string): void {
        this.productService.remove(api.PRODUCT, id)
            .subscribe(
                () => this.load(),
                error => this.logError(error));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['product/product-update', identifier]);
    }

    onDetails(product: Product) {
        if (product)
            this.selectedProduct = product;
    }

    onChangePageSize(): void {
        this.createPagination();
    }

    logError(error: Error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
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

    constructor(private productService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.productService.loadAll(api.PRODUCT)
            .subscribe(
                productList => this.productList = productList,
                err => this.logError(err));
    }

    onDelete(identifier: number): void {
        this.productService.remove(api.PRODUCT, identifier)
            .subscribe(
                () => this.loadData(),
                err => this.logError(err));
    }

    onEdit(identifier: number): void {
        identifier && this.router.navigate(['product/product-update', identifier]);
    }

    logError(err) {
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}
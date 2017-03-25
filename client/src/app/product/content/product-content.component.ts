import {Component, OnInit} from "@angular/core";
import {Product} from "../../model/product";
import {SecurityService} from "../../security/security.service";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";

@Component({
    selector: 'product-content-component',
    templateUrl: 'product-content.component.html'
})
export class ProductContentComponent implements OnInit {
    productList: Product[];

    constructor(private productService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.productService.loadAllUnauthorized(environment.PRODUCT_URL)
            .subscribe(productList => this.productList = productList);
    }

    onDelete(product: Product): void {
        this.productService.remove(environment.PRODUCT_URL, product.id)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(product: Product): void {
        product && this.router.navigate(['product/product-update', product.id]);
    }

    isAdmin(): boolean {
        return SecurityService.isAdmin();
    }
}
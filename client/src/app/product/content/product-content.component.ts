import {Component, OnInit} from "@angular/core";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {CommonComponent} from "../../common/common.component";

@Component({
    selector: 'product-content-component',
    templateUrl: 'product-content.component.html'
})
export class ProductContentComponent extends CommonComponent implements OnInit{
    productList: Product[];

    constructor(private productService: CommonService, private router: Router) {
        super();
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.productService.loadAllUnauthorized(api.PRODUCT)
            .subscribe(productList => this.productList = productList);
    }

    onDelete(product: Product): void {
        this.productService.remove(api.PRODUCT, product.id)
            .subscribe(result => result ? this.loadData() : alert("Error!"),
                error => alert(error));
    }

    onEdit(product: Product): void {
        product && this.router.navigate(['product/product-update', product.id]);
    }

}
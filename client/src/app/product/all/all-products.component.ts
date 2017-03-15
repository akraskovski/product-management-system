import {Component, OnInit} from "@angular/core";
import {ProductService} from "../product.service";
import {Product} from "../../model/product";

@Component({
    selector: 'all-products-component',
    templateUrl: './all-products.component.html'
})
export class AllProductsComponent implements OnInit {

    productList: Product[];

    constructor(private productService: ProductService) {
    }

    ngOnInit(): void {
        this.productService.loadAll()
            .then(productList => this.productList = productList);
    }
}
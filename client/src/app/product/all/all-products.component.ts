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
        this.loadData();
    }

    private loadData() {
        this.productService.loadAll()
            .subscribe(productList => this.productList = productList);
    }

    onDelete(product: Product): void {
        this.productService.remove(product.id)
            .subscribe(result => {
                if (result === true) {
                    this.loadData();
                } else {
                    alert("Error!");
                }
            }, error => alert(error));
    }
}
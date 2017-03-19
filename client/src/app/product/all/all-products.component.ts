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
            .subscribe(productList => this.productList = productList);
    }

    onDelete(identifier: number) {
        this.productService.remove(identifier)
            .subscribe(result => {
                if (result === true) {
                    alert("Success!");
                } else {
                    alert("Error!");
                }
            }, error => alert(error));
    }
}
import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {ProductService} from "../product.service";

@Component({
    selector: 'product-search-component',
    templateUrl: './product-search.component.html'
})
export class ProductSearchComponent {

    findProducts: Product[];
    inputText: string;

    constructor(private productService: ProductService) {
    }

    loadProductByName(): void {
        this.productService.loadByName(this.inputText)
            .subscribe(responce => this.findProducts = responce);
    }
}
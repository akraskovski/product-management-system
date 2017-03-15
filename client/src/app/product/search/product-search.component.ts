import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {ProductService} from "../product.service";

@Component({
    selector: 'product-search-component',
    templateUrl: './product-search.component.html'
})
export class ProductSearchComponent {

    findProduct: Product;
    inputText: string;

    constructor(private productService: ProductService) {
    }

    loadProductByName(): void {
        this.productService.loadByName(this.inputText)
            .then(responce => {
                this.findProduct = responce;
                alert(`
                    NAME: ${this.findProduct.name}\n
                    COST: ${this.findProduct.cost}\n
                    TYPE: ${this.findProduct.type}
                `);
            })
    }
}
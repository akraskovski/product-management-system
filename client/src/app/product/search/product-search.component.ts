import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {ProductService} from "../product.service";
import {SecurityService} from "../../security/security.service";
import {Router} from "@angular/router";

@Component({
    selector: 'product-search-component',
    templateUrl: './product-search.component.html'
})
export class ProductSearchComponent {

    findProducts: Product[];
    inputText: string;

    constructor(private productService: ProductService, private router: Router) {
    }

    onSubmit(): void {
        this.productService.loadByName(this.inputText)
            .subscribe(responce => this.findProducts = responce);
    }

    onDelete(product: Product): void {
        this.productService.remove(product.id)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['product/product-content']);
                } else {
                    alert("Error!");
                }
            }, error => alert(error));
    }

    onEdit(product: Product): void {
        if (product)
            this.router.navigate(['product/product-update', product.id]);
    }

    isAdmin(): boolean{
        return SecurityService.isAdmin();
    }
}
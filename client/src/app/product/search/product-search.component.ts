import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {SecurityService} from "../../security/security.service";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";

@Component({
    selector: 'product-search-component',
    templateUrl: './product-search.component.html'
})
export class ProductSearchComponent {

    findProducts: Product[];
    inputText: string;

    constructor(private productService: CommonService, private router: Router) {
    }

    onSubmit(): void {
        this.productService.loadByName(environment.PRODUCT_URL, this.inputText)
            .subscribe(responce => this.findProducts = responce);
    }

    onDelete(identifier): void {
        this.productService.remove(environment.PRODUCT_URL, identifier)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['product/product-content']);
                } else {
                    alert("Error!");
                }
            }, error => alert(error));
    }

    onEdit(identifier: number): void {
        if (identifier)
            this.router.navigate(['product/product-update', identifier]);
    }

    isAdmin(): boolean{
        return SecurityService.isAdmin();
    }
}
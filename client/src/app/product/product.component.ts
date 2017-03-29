import {Component} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: 'product-component',
    templateUrl: './product.component.html'
})
export class ProductComponent {
    keyword: string = '';

    constructor(private router: Router) {
    }

    onSearchClick(): void {
        this.router.navigate(['product/product-search', this.keyword]);
    }
}
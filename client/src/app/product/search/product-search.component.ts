import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {AuthorityWorker} from "../../common/authority-worker";

@Component({
    selector: 'product-search-component',
    templateUrl: './product-search.component.html'
})
export class ProductSearchComponent extends AuthorityWorker {
    keyword: string;
    findProducts: Product[];

    constructor(private productService: CommonService, private router: Router) {
        super();
    }

    onSubmit(): void {
        this.productService.loadByName(api.PRODUCT, this.keyword)
            .subscribe(
                response => this.findProducts = response,
                err => this.logError(err)
            );
    }

    onDelete(id: number): void {
        this.productService.remove(api.PRODUCT, id)
            .subscribe(result => result ? this.router.navigate(['product/product-content']) : alert("Error!"),
                error => alert(error));
    }

    onEdit(id: number): void {
        id ? this.router.navigate(['product/product-update', id]) : alert("Wrong ID!");
    }

    logError(error) {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}
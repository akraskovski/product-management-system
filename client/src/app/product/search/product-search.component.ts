import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
@Component({
    selector: 'product-search-component',
    templateUrl: 'product-search.component.html'
})
export class ProductSearchComponent {
    keyword: string;
    selectedParameter: string;
    availableParameters: string[];
    findProducts: Product[];
    selectedProduct: Product;

    constructor(private productService: CommonService, private router: Router) {
        this.availableParameters = ['Name', 'Type'];
        this.selectedParameter = this.availableParameters[0];
    }

    onSubmit(): void {
        if (this.keyword) {
            switch (this.selectedParameter) {
                case 'Name': {
                    console.log("search by name");
                    this.productService.loadByName(api.PRODUCT, this.keyword)
                        .subscribe(
                            response => this.findProducts = response,
                            error => this.logError(error)
                        );
                    break;
                }
                case 'Type': {
                    console.log("search by type");
                    this.productService.loadByType(api.PRODUCT, this.keyword)
                        .subscribe(
                            response => this.findProducts = response,
                            error => this.logError(error)
                        );
                    break;
                }
            }
        }
    }

    getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }

    onDetails(product: Product) {
        if (product)
            this.selectedProduct = product;
    }

    onDelete(id: number): void {
        this.productService.remove(api.PRODUCT, id)
            .subscribe(result => result ? this.router.navigate(['product/product-content']) : alert("Error!"),
                error => alert(error));
    }

    onEdit(id: number): void {
        id ? this.router.navigate(['product/product-update', id]) : alert("Wrong ID!");
    }

    logError(error): void {
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}